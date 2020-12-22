package com.mihey.servletproject.controller;

import com.google.gson.Gson;
import com.mihey.servletproject.model.Region;
import com.mihey.servletproject.repository.RegionRepository;
import com.mihey.servletproject.repository.hibernate.RegionRepositoryImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/regions"})
public class RegionController extends HttpServlet {

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
        Region region = g.fromJson(json, Region.class);
        regionController.save(region);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        Region region = g.fromJson(json, Region.class);
        regionController.update(region);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        regionController.deleteById(id);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if ( id == null) {
            List<Region> list = regionController.getAll();
            response.getWriter().write(list.toString());
        } else {
            int regionId = Integer.parseInt(id);
            Region region = regionController.getById(regionId);
            response.getWriter().write(region.toString());
        }
    }

    @Override
    public void destroy() {
    }

}



