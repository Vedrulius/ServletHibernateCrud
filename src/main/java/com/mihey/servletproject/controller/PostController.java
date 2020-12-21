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

@WebServlet(urlPatterns = {"/posts/create", "/posts/update", "/posts/delete", "/posts/list", "/posts/find"})
public class PostController extends HttpServlet {

    private PostRepository postController = new PostRepositoryImpl();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        String json = req.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        Post post = g.fromJson(json, Post.class);
        if (path.equals("/posts/create")) {
            post.setCreated(new Timestamp(System.currentTimeMillis()));
            post.setUpdated(new Timestamp(System.currentTimeMillis()));
            postController.save(post);
        }
        if (path.equals("/posts/update")) {
            String content = post.getContent();
            post = postController.getById(post.getId());
            post.setUpdated(new Timestamp(System.currentTimeMillis()));
            post.setContent(content);
            postController.update(post);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/posts/delete":
                deletePost(request, response);
                break;
            case "/posts/list":
                listPosts(request, response);
                break;
            case "/posts/find":
                findPost(request, response);
                break;
        }
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        postController.deleteById(id);

    }

    private void listPosts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Post> list = postController.getAll();
        response.getWriter().write(list.toString());

    }

    private void findPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Post post = postController.getById(id);
        response.getWriter().write(post.toString());
    }

    @Override
    public void destroy() {
    }
}



