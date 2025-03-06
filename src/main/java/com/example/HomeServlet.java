package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A debugging servlet that displays information about the request
 */
@WebServlet(urlPatterns = {"/home", "/"})
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Debug Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Debug Information</h1>");
        
        // Display servlet info
        out.println("<h2>Servlet Information</h2>");
        out.println("<p>Servlet Path: " + request.getServletPath() + "</p>");
        out.println("<p>Context Path: " + request.getContextPath() + "</p>");
        
        // Try to find index.html
        boolean indexExists = request.getServletContext().getResource("/index.html") != null;
        out.println("<p>index.html exists: " + indexExists + "</p>");
        
        // List all available resources in the webapp
        out.println("<h2>Available Resources</h2>");
        out.println("<p>If any resources show up below, they are accessible:</p>");
        out.println("<pre>");
        try {
            java.util.Set<String> paths = request.getServletContext().getResourcePaths("/");
            if (paths != null) {
                for (String path : paths) {
                    out.println(path);
                }
            } else {
                out.println("No resources found in root directory.");
            }
        } catch (Exception e) {
            out.println("Error listing resources: " + e.getMessage());
        }
        out.println("</pre>");
        
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
