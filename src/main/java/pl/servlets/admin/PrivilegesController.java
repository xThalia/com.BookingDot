package pl.servlets.admin;

import connectors.DbConnector;
import model.User;
import providers.UserDataProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PrivilegesController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(session.getAttribute("currentSessionUser") != null ) {
            int a= 0; int b= 0;
            List<User> userList = DbConnector.getAllUser(); // Obtain all products.
             DbConnector.changeUserPrivilege(a,b );
            request.setAttribute("userList", userList); // Store products in request scope.


            if (userList.isEmpty()) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/admin/privileges.jsp").forward(request, response);
              }

        else {
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
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser",id);
            response.sendRedirect("views/home.jsp");
        } else {
            response.sendRedirect("views/auth/login.jsp?status=error");
        }
    }
}
