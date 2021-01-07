package pl.servlets.hotel;

import connectors.DbConnector;
import enums.Privilege;
import model.Hotel;
import model.User;
import providers.UserDataProvider;
import services.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddHotelController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(session.getAttribute("currentSessionUser") != null ) {
            response.sendRedirect("views/hotel/add-hotel.jsp");
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String name    = request.getParameter("name");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        boolean status;
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setCity(city);
        DbConnector.addHotel(hotel, (int)session.getAttribute("currentSessionUser"));

        if(session.getAttribute("currentSessionUser") != null ) {
            List<Hotel> hotelList = DbConnector.getAllUserHotel((int)session.getAttribute("currentSessionUser")); // Obtain all products.
            request.setAttribute("hotelList", hotelList); // Store products in request scope.
            if (hotelList.isEmpty()) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/hotels.jsp?status=success").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
