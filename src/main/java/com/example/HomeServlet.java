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

        String path = request.getRequestURI();
        LOGGER.info("Received request: " + path);

        // Only redirect requests for "/" and not for index.html or other static files
        if (path.equals("/") || path.equals("/index")) {
            response.sendRedirect("index.html");
        } else {
            // Let Tomcat serve the static file normally
            getServletContext().getRequestDispatcher(path).forward(request, response);
        }
    }
}
