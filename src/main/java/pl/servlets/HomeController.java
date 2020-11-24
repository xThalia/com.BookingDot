package pl.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession(true);
        if(session.getAttribute("currentSessionUser") != null) {
            response.sendRedirect("views/home.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
