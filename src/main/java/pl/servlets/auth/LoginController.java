package pl.servlets.auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();
        writer.println("XD");
        response.sendRedirect("views/auth/login.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();
        writer.println("XD");
    }
}
