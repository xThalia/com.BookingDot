package pl.servlets.hotel;

import connectors.DbConnector;
import model.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class EditHotelController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        int hotelId = Integer.parseInt(request.getParameter("hotelId"));

        if(session.getAttribute("currentSessionUser") != null ) {
            List<Room> hotelRoomList = DbConnector.getAllHotelRooms(hotelId);
            int userId  = (int)session.getAttribute("currentSessionUser");

            request.setAttribute("hotelRoomList", hotelRoomList);
            request.setAttribute("hotel", DbConnector.getHotelByIdAndUserId(hotelId, userId));
            if (hotelRoomList == null || hotelRoomList.size() == 0) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/edit-hotel.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
