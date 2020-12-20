package com.mihey.servletproject.view;

import com.google.gson.Gson;
import com.mihey.servletproject.controller.PostController;
import com.mihey.servletproject.model.Post;
import com.mihey.servletproject.model.Region;
import com.mihey.servletproject.model.Role;
import com.mihey.servletproject.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/posts"})
public class PostServlet extends HttpServlet {

    private PostController postController = new PostController();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        resp.getWriter().write(path);
        String json = req.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        Post post = g.fromJson(json, Post.class);
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        post.setUpdated(new Timestamp(System.currentTimeMillis()));
        postController.createPost(post);
        System.out.println(post);
    }

    @Override
    public void destroy() {
    }
}
