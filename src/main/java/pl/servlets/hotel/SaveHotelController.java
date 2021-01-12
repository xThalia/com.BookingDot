package pl.servlets.hotel;

import connectors.DbConnector;
import model.Hotel;
import providers.HotelProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SaveHotelController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");

        int hotelId    = Integer.parseInt(request.getParameter("hotelId"));
        String name    = request.getParameter("name");
        String address = request.getParameter("address");
        String city    = request.getParameter("city");

        if(session.getAttribute("currentSessionUser") != null ) {
            HotelProvider hotel = new HotelProvider();
            hotel.editHotel(hotelId, name, address, city);

            List<Hotel> hotelList = DbConnector.getAllUserHotel((int)session.getAttribute("currentSessionUser"));
            request.setAttribute("hotelList", hotelList);

            if (hotelList == null) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/hotels.jsp?status=successEdit").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
