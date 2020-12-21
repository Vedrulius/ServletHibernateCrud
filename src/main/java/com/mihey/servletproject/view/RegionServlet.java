package com.mihey.servletproject.view;

import com.google.gson.Gson;
import com.mihey.servletproject.controller.RegionController;
import com.mihey.servletproject.model.Region;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/regions/create", "/regions/update", "/regions/delete", "/regions/list", "/regions/find"})
public class RegionServlet extends HttpServlet {

    private RegionController regionController = new RegionController();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        response.getWriter().write(path);
        String json = request.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        Region region = g.fromJson(json, Region.class);
        if (path.equals("/regions/create")) {
            regionController.saveRegion(region);
        }
        if (path.equals("/regions/update")) {
            regionController.editRegion(region);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        response.getWriter().write(path);
        switch (path) {
            case "/regions/delete":
                deleteRegion(request, response);
                break;
            case "/regions/list":
                listRegions(request, response);
                break;
            case "/regions/find":
                findRegion(request, response);
                break;
        }
    }

    private void deleteRegion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        regionController.deleteRegionById(id);

    }

    private void listRegions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Region> list = regionController.getAllRegions();
        response.getWriter().write(list.toString());

    }

    private void findRegion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Region region = regionController.getRegionById(id);
        response.getWriter().write(region.toString());
    }

    @Override
    public void destroy() {
    }

}
