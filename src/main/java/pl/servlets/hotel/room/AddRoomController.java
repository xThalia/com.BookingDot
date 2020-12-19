package pl.servlets.hotel.room;

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

public class AddRoomController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(session.getAttribute("currentSessionUser") != null ) {
            int hotelId = Integer.parseInt(request.getParameter("hotel"));

            request.setAttribute("hotelId", hotelId);
            request.getRequestDispatcher("views/hotel/room/add-room.jsp").forward(request, response);
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession session = request.getSession(true);
        String name  = request.getParameter("name");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        int price    = Integer.parseInt(request.getParameter("price"));
        int hotelId  = Integer.parseInt(request.getParameter("hotelId"));

        if(session.getAttribute("currentSessionUser") != null ) {
            int userId  = (int)session.getAttribute("currentSessionUser");

            Room room = new Room();
            room.setName(name);
            room.setCapacity(capacity);
            room.setPrice(price);
            room.setHotelId(hotelId);
            DbConnector.addRoom(room);

            List<Room> hotelRoomList = DbConnector.getAllHotelRooms(hotelId);
            request.setAttribute("hotelRoomList", hotelRoomList);
            request.setAttribute("hotel", DbConnector.getHotelByIdAndUserId(hotelId, userId));
            if (hotelRoomList == null) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/edit-hotel.jsp?status=success").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
