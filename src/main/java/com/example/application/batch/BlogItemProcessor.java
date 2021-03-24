package com.example.application.batch;


import java.time.LocalDate;
import java.util.Date;

import com.example.application.data.entity.Blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class BlogItemProcessor implements ItemProcessor<Blog, Blog> {

  private static final Logger log = LoggerFactory.getLogger(BlogItemProcessor.class);

  @Override
  public Blog process(final Blog blog) throws Exception {
    final String title = blog.getTitle();
    final String content = blog.getContent();
    final String author = blog.getAuthor();
    final LocalDate date = LocalDate.now();
    
    final Blog transformedBlog = new Blog(title, content, author, date);

    log.info("Converting (" + blog + ") into (" + transformedBlog + ")");

    return transformedBlog;
  }

}