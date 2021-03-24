package com.example.application.data.entity;

import java.time.LocalDate;

public class Blog {

    private String title;
    private String content;
    private String author;
    private LocalDate publication_date;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public LocalDate getPublication_date() {
        return publication_date;
    }
    public void setPublication_date(LocalDate publication_date) {
        this.publication_date = publication_date;
    }
    public Blog(String title, String content, String author, LocalDate publication_date) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.publication_date = publication_date;
    }
    public Blog(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public Blog(String title) {
        this.title = title;
    }

    public Blog() {
    }
}
