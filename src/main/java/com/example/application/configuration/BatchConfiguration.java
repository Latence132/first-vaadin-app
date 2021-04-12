package com.example.application.configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.batch.core.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.application.batch.BlogItemProcessor;
import com.example.application.batch.JobCompletionNotificationListener;
import com.example.application.data.entity.Blog;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Blog> reader() {
        return new FlatFileItemReaderBuilder<Blog>().name("BlogItemReader").resource(new ClassPathResource("blog.csv"))
                .delimited().names(new String[] { "title", "content", "author" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Blog>() {
                    {
                        setTargetType(Blog.class);
                    }
                }).build();
    }

    @Bean
    public BlogItemProcessor processor() {
        return new BlogItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Blog> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Blog>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO BLOG (title, content, author, publication_date) VALUES (:title, :content, :author, :publication_date)")
                .dataSource(dataSource).build();
    }

    @Bean
    public Job importBlogJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importBlogJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
                .end().build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Blog> writer) {
        return stepBuilderFactory.get("step1").<Blog, Blog>chunk(10).reader(reader()).processor(processor())
                .writer(writer).build();
    }


}