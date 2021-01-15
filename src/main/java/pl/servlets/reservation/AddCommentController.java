package pl.servlets.reservation;

import connectors.DbConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCommentController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        int hotelId  = Integer.parseInt(request.getParameter("hotelId"));
        String comment  = request.getParameter("comment");
        if(session.getAttribute("currentSessionUser") != null ) {
            int userId  = (int)session.getAttribute("currentSessionUser");
            DbConnector.addComment(comment,hotelId,userId);
            request.getRequestDispatcher("views/home.jsp?status=successComment").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
