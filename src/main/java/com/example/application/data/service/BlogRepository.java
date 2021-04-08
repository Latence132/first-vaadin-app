package com.example.application.data.service;

import com.example.application.data.entity.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}