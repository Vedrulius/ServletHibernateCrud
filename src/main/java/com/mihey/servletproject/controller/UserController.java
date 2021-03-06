package com.mihey.servletproject.controller;

import com.google.gson.Gson;
import com.mihey.servletproject.model.Region;
import com.mihey.servletproject.model.Role;
import com.mihey.servletproject.model.User;
import com.mihey.servletproject.repository.RegionRepository;
import com.mihey.servletproject.repository.UserRepository;
import com.mihey.servletproject.repository.hibernate.RegionRepositoryImpl;
import com.mihey.servletproject.repository.hibernate.UserRepositoryImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/users"})
public class UserController extends HttpServlet {

    private UserRepository userController = new UserRepositoryImpl();
    private RegionRepository regionController = new RegionRepositoryImpl();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        User user = g.fromJson(json, User.class);
        Region region = regionController.save(user.getRegion());
        user.setRegion(region);
        user.setRole(Role.USER);
        userController.save(user);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        User user = g.fromJson(json, User.class);
        Region region = regionController.save(user.getRegion());
        user.setRegion(region);
        user.setRole(Role.USER);
        userController.update(user);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            List<User> list = userController.getAll();
            response.getWriter().write(list.toString());
        } else {
            int userId = Integer.parseInt(id);
            User user = userController.getById(userId);
            response.getWriter().write(user.toString());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        userController.deleteById(id);
    }

    @Override
    public void destroy() {
    }
}


