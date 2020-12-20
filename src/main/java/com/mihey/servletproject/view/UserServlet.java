package com.mihey.servletproject.view;

import com.google.gson.Gson;
import com.mihey.servletproject.controller.RegionController;
import com.mihey.servletproject.controller.UserController;
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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/"})
public class UserServlet extends HttpServlet {

    private UserController userController = new UserController();
    private RegionController regionController = new RegionController();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        String json = request.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        User user = g.fromJson(json, User.class);
        if (path.equals("/users/create")) {
            Region region = regionController.saveRegion(user.getRegion());
            user.setRegion(region);
            user.setRole(Role.USER);
            userController.saveUser(user);
        }
        if (path.equals("/users/update")) {
            Region region = regionController.saveRegion(user.getRegion());
            user.setRegion(region);
            user.setRole(Role.USER);
            userController.editUser(user);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();


    }

    @Override
    public void destroy() {
    }
}
