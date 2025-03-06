package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.logging.Logger;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(HomeServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Log request details
        LOGGER.info("Received request: " + request.getRequestURI());

        try {
            // Redirecting the request to index.html
            response.sendRedirect("/index.html");
        } catch (Exception e) {
            // Log and send an error if redirecting fails
            LOGGER.severe("Error redirecting to index.html: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error redirecting to index.html");
        }
    }
}
