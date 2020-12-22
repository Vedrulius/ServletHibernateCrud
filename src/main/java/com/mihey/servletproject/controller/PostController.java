package com.mihey.servletproject.controller;

import com.google.gson.Gson;
import com.mihey.servletproject.model.Post;
import com.mihey.servletproject.repository.PostRepository;
import com.mihey.servletproject.repository.hibernate.PostRepositoryImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/posts"})
public class PostController extends HttpServlet {

    private PostRepository postController = new PostRepositoryImpl();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        Post post = g.fromJson(json, Post.class);
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        post.setUpdated(new Timestamp(System.currentTimeMillis()));
        postController.save(post);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        Post post = g.fromJson(json, Post.class);
        String content = post.getContent();
        post = postController.getById(post.getId());
        post.setUpdated(new Timestamp(System.currentTimeMillis()));
        post.setContent(content);
        postController.update(post);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            List<Post> list = postController.getAll();
            response.getWriter().write(list.toString());
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Post post = postController.getById(id);
            response.getWriter().write(post.toString());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        postController.deleteById(id);

    }

    @Override
    public void destroy() {
    }
}



