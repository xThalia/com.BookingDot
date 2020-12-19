package pl.servlets.hotel;

import connectors.DbConnector;
import model.Hotel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class HotelsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(session.getAttribute("currentSessionUser") != null ) {
            List<Hotel> hotelList = DbConnector.getAllUserHotel((int)session.getAttribute("currentSessionUser"));
            request.setAttribute("hotelList", hotelList);
            if (hotelList.isEmpty()) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/hotels.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
