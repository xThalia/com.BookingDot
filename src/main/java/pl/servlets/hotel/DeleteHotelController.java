package pl.servlets.hotel;

import connectors.DbConnector;
import model.Hotel;
import providers.HotelProvider;
import providers.UserDataProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteHotelController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        int hotelId    = Integer.parseInt(request.getParameter("hotelId"));

        if(session.getAttribute("currentSessionUser") != null ) {
            HotelProvider hotel = new HotelProvider();
            hotel.deleteHotel(hotelId);

            List<Hotel> hotelList = DbConnector.getAllUserHotel((int)session.getAttribute("currentSessionUser"));
            request.setAttribute("hotelList", hotelList);

            if (hotelList == null) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/hotels.jsp?status=successDelete").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
