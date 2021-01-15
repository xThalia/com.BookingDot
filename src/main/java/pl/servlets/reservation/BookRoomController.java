package pl.servlets.reservation;

import connectors.DbConnector;
import model.Room;
import pl.servlets.hotel.room.AddRoomController;
import providers.RoomProvider;
import tools.UsefulFunctions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class BookRoomController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");

        int roomId  = Integer.parseInt(request.getParameter("roomId"));
        String startDate  = request.getParameter("startDate");
        String endDate  = request.getParameter("endDate");
        System.out.println("START: " + startDate);
        System.out.println("END: " + endDate);
        if(session.getAttribute("currentSessionUser") != null ) {
            int userId  = (int)session.getAttribute("currentSessionUser");
            DbConnector.addReservation(roomId,userId, startDate, endDate,0,false);
            request.getRequestDispatcher("views/home.jsp?status=successBook").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
