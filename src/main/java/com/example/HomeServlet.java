@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            System.out.println("Request received at HomeServlet");
            request.getRequestDispatcher("/index.html").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Logs the error to console
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
        }
    }
}
