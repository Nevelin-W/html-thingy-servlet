package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Forwarding the request to index.html (make sure the path is correct)
        try {
            request.getRequestDispatcher("index.html").forward(request, response);
        } catch (Exception e) {
            // Log and send an error if forwarding fails
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error forwarding to index.html");
        }
    }
}
