package pl.servlets.auth;

import services.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TokenController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("currentSessionUser") != null ) {
            response.sendRedirect("views/home.jsp");
        } else {
            response.sendRedirect("views/auth/token.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login    = request.getParameter("email");
        String token    = request.getParameter("token");
        RegisterService registerService = new RegisterService();

        System.out.println(login + " token " + token);
        System.out.println(registerService.verifyUserToken(login, token));

        if (registerService.verifyUserToken(login, token) != 0) {
            System.out.println("ok token gut");
            response.sendRedirect("views/auth/login.jsp?token=success");
        } else {
            System.out.println("nie ok");
            response.sendRedirect("views/auth/login.jsp?token=error");
        }
    }
}
