package pl.servlets.hotel.room;

import connectors.DbConnector;
import model.Hotel;
import model.Room;
import providers.HotelProvider;
import providers.RoomProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SaveRoomController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        int roomId   = Integer.parseInt(request.getParameter("roomId"));
        String name  = request.getParameter("name");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        int price    = Integer.parseInt(request.getParameter("price"));
        int hotelId  = Integer.parseInt(request.getParameter("hotelId"));


        if(session.getAttribute("currentSessionUser") != null ) {
            RoomProvider room = new RoomProvider();
            room.editRoom(roomId, name, capacity, price);

            List<Room> hotelRoomList = DbConnector.getAllHotelRooms(hotelId);
            int userId  = (int)session.getAttribute("currentSessionUser");
            request.setAttribute("hotelRoomList", hotelRoomList);
            request.setAttribute("hotel", DbConnector.getHotelByIdAndUserId(hotelId, userId));
            if (hotelRoomList == null) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/edit-hotel.jsp?status=successEdit").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
