package pl.servlets.occupation;

import connectors.DbConnector;
import model.FreeAndOccupiedRooms;
import model.Hotel;
import providers.UserDataProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CurrentOccupationController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(session.getAttribute("currentSessionUser") != null ) {
            int userId  = (int)session.getAttribute("currentSessionUser");

            List<FreeAndOccupiedRooms> freeAndOccupiedRoomsList = DbConnector.divideFreeAndOccupiedHotelRooms(userId);
            request.setAttribute("freeAndOccupiedRoomsList", freeAndOccupiedRoomsList);


            if (freeAndOccupiedRoomsList == null || freeAndOccupiedRoomsList.isEmpty()) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/occupation/rooms-occupancy.jsp").forward(request, response);
        }

        else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
