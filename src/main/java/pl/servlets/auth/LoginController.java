package pl.servlets.auth;

import connectors.DbConnector;
import providers.UserDataProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("currentSessionUser") != null ) {
            response.sendRedirect("views/home.jsp");
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       String login    = request.getParameter("email");
       String password = request.getParameter("password");
       int id          = 0;
       UserDataProvider userDataProvider = new UserDataProvider();

       System.out.println(login + " password " + password);
       System.out.println(userDataProvider.authenticateUserWithLoginAndPassword(login, password));
       if (userDataProvider.authenticateUserWithLoginAndPassword(login, password) != 0) {
           id = userDataProvider.authenticateUserWithLoginAndPassword(login, password);
           DbConnector.sendOfferToUser(id);
           HttpSession session = request.getSession(true);
           session.setAttribute("currentSessionUser",id);
           response.sendRedirect("views/home.jsp");
       } else {
           response.sendRedirect("views/auth/login.jsp?status=error");
        }
    }
}
