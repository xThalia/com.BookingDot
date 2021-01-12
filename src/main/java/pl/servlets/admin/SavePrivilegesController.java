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

public class SavePrivilegesController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession session = request.getSession();

        int userId   = Integer.parseInt(request.getParameter("userId"));
        int privilege  = Integer.parseInt(request.getParameter("privilege"));


        if(session.getAttribute("currentSessionUser") != null ) {
            UserDataProvider userp = new UserDataProvider();
            userp.changeUserPrivilege( userId,  privilege);

            List<User> userList= DbConnector.getAllUser();
            request.setAttribute("userList", userList);

            if (userList == null) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/admin/privileges.jsp?status=successEdit").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}