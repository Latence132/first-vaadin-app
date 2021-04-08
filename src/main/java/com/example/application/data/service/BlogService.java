package com.example.application.data.service;

import com.example.application.data.entity.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;

@Service
public class BlogService extends CrudService<Blog, Integer> {

    private BlogRepository repository;

    public BlogService(@Autowired BlogRepository repository) {
        this.repository = repository;
    }

    @Override
    public BlogRepository getRepository() {
        return repository;
    }

}
