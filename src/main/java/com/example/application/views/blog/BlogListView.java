package com.example.application.views.blog;

import java.time.LocalDate;
import java.util.List;

import com.example.application.data.entity.Blog;
import com.example.application.data.service.BlogService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CssImport("./views/cardlist/card-list-view.css")
@Route(value = "Blog-list", layout = MainView.class)
@PageTitle("Blog List")
public class BlogListView extends Div implements AfterNavigationObserver {

    private static final Logger log = LoggerFactory.getLogger(BlogListView.class);

    Grid<Blog> grid = new Grid<>();

    public BlogListView(BlogService blogService) {
        addClassName("Blog-list-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        List<Blog> blogs = blogService.getRepository().findAll();
        grid.addComponentColumn(blog -> createBlog(blog));
        

        grid.setItems(blogs);

        add(grid);
    }

    private HorizontalLayout createBlog(Blog blog) {
        HorizontalLayout Blog = new HorizontalLayout();
        Blog.addClassName("Blog");
        Blog.setSpacing(false);
        Blog.getThemeList().add("spacing-s");

        Image image = new Image();
        // image.setSrc(blog.getImage());
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(blog.getTitle());
        name.addClassName("name");
        Span date = new Span(blog.getPublication_date().toString());
        date.addClassName("date");
        header.add(name, date);

        Span post = new Span(blog.getAuthor());
        post.addClassName("post");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        IronIcon likeIcon = new IronIcon("vaadin", "heart");
        Span likes = new Span();
        likes.addClassName("likes");
        IronIcon commentIcon = new IronIcon("vaadin", "comment");
        Span comments = new Span();
        comments.addClassName("comments");
        IronIcon shareIcon = new IronIcon("vaadin", "connect");
        Span shares = new Span();
        shares.addClassName("shares");

        actions.add(likeIcon, likes, commentIcon, comments, shareIcon, shares);

        description.add(header, post, actions);
        Blog.add(image, description);
        return Blog;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        // Set some data when this view is displayed.
        log.info("BlogListView loaded");
    }

    private static Blog createBlog(String author, String content, LocalDate publication_date) {
        Blog p = new Blog();
        p.setAuthor(author);
        p.setContent(content);
        p.setPublication_date(publication_date);


        return p;
    }

}
