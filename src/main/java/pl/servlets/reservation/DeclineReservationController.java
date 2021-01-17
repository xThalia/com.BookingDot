package pl.servlets.reservation;

import connectors.DbConnector;
import model.Hotel;
import model.ReservationInfoToShow;
import providers.HotelProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeclineReservationController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        int reservationId    = Integer.parseInt(request.getParameter("reservationId"));
        int isAccepted    = Integer.parseInt(request.getParameter("isAccepted"));
        ReservationInfoToShow reservationInfo = DbConnector.getReservationInfoByReservationId(reservationId);

        if(session.getAttribute("currentSessionUser") != null ) {
            DbConnector.updateReservationWithConfirmation(reservationInfo, isAccepted);

            List<Hotel> hotelList = DbConnector.getAllUserHotel((int)session.getAttribute("currentSessionUser"));
            request.setAttribute("hotelList", hotelList);

            if (hotelList == null) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/reservation/manage-reservations.jsp?status=successConfirmation").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
