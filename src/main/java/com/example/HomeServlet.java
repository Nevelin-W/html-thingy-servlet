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
        
        LOGGER.info("Received request: " + request.getRequestURI());

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Test Page</title></head><body>");
        out.println("<h1>Servlet is Working!</h1>");
        out.println("</body></html>");
    }
}
