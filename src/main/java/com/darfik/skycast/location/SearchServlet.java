package com.darfik.skycast.location;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocationService locationService = LocationServiceFactory.build();
        LocationDTO locationDTO = new LocationDTO(req.getParameter("location"));
        resp.getWriter().print(locationService.getLocationByName(locationDTO).getName());

    }
}
