package pl.servlets.auth;

import connectors.DbConnector;
import enums.Privilege;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.sendRedirect("views/auth/register.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = new User();
        user.setLogin(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setFirst_name(request.getParameter("firstName"));
        user.setLast_name(request.getParameter("lastName"));
        user.setEmail_confirmed(true);
        user.setUser_privilege(Privilege.ORDINARY);
        user.setTimestamp(123);
        DbConnector.addUser(user);
        response.sendRedirect("views/auth/login.jsp");
    }
}
