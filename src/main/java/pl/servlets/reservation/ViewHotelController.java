package pl.servlets.reservation;

import connectors.DbConnector;
import model.Hotel;
import model.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewHotelController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        List<Room> hotelRoomList;
        int hotelId;
        String startDate, endDate;
        Hotel hotel;

        hotelId = Integer.parseInt(request.getParameter("hotelId"));
        hotel = DbConnector.getHotelById(hotelId);
        request.setAttribute("hotel", hotel);

        startDate = request.getParameter("startDate");
        endDate = request.getParameter("endDate");

        hotelRoomList = DbConnector.getFreeRoomsByHotelId(hotelId, startDate, endDate);
        request.setAttribute("hotelRoomList", hotelRoomList);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);

        System.out.println(hotelRoomList);
        System.out.println(hotelId);
        System.out.println(startDate);
        System.out.println(endDate);
        if (hotelRoomList == null || hotelRoomList.isEmpty()) {
            request.setAttribute("emptyList", "true");
        } else {
            request.setAttribute("emptyList", "false");
        }

        request.getRequestDispatcher("views/reservation/view-hotel.jsp").forward(request, response);
    }
}
