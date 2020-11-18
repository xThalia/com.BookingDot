package pl.servlets.auth;

import providers.UserDataProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
       String login    = request.getParameter("email");
       String password = request.getParameter("password");
       int id          = 0;
       UserDataProvider userDataProvider = new UserDataProvider();

       System.out.println(login + " password " + password);
       if (userDataProvider.authenticateUserWithLoginAndPassword(login, password) != 0) {
           System.out.println("ok2");
           id = userDataProvider.authenticateUserWithLoginAndPassword(login, password);
           HttpSession session = request.getSession(true);
           session.setAttribute("currentSessionUser",id);
           response.sendRedirect("testredirect.jsp");
       } else {
           System.out.println("nie ok");
           request.getSession().setAttribute("status", "error");
           response.sendRedirect("views/auth/login.jsp");
        }
    }
}
