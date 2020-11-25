package pl.servlets.auth;

import enums.Privilege;
import model.User;
import services.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("currentSessionUser") != null ) {
            response.sendRedirect("views/home.jsp");
        } else {
            response.sendRedirect("views/auth/register.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        boolean status;
        User user = new User();
        user.setLogin(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setFirst_name(request.getParameter("firstName"));
        user.setLast_name(request.getParameter("lastName"));
        user.setEmail_confirmed(false);
        user.setUser_privilege(Privilege.ORDINARY);
        user.setTimestamp(123);
        RegisterService registerService = new RegisterService();
        status = registerService.registerUserAndSendToken(user);
        System.out.println(status);

        if(status) {
            response.sendRedirect("views/auth/login.jsp?status=success");
        }

    }
}
