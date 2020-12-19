package pl.servlets.hotel.room;

import connectors.DbConnector;
import model.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditRoomController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        if(session.getAttribute("currentSessionUser") != null ) {

            request.setAttribute("room", DbConnector.getRoomByIdAndHotelId(roomId, hotelId));
            request.setAttribute("hotelId", hotelId);
            request.getRequestDispatcher("views/hotel/room/edit-room.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
