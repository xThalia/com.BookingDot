package pl.servlets.reservation;

import connectors.DbConnector;
import model.Hotel;
import model.Reservation;
import model.ReservationInfoToShow;
import providers.HotelProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AcceptReservationController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        int reservationId    = Integer.parseInt(request.getParameter("reservationId"));
        int isAccepted    = Integer.parseInt(request.getParameter("isAccepted"));
        ReservationInfoToShow reservationInfo = DbConnector.getReservationInfoByReservationId(reservationId);

        if(session.getAttribute("currentSessionUser") != null ) {
            DbConnector.updateReservationWithConfirmation(reservationInfo, isAccepted);

            int userId  = (int)session.getAttribute("currentSessionUser");
            List<Reservation> reservations = DbConnector.getAllCurrentAndUpcomingReservationsByOwnerId(userId);

            List<ReservationInfoToShow> reservationsInfo;
            if(reservations != null && reservations.size() != 0)
                reservationsInfo = DbConnector.getAllReservationsInfo(reservations);// Obtain all products.
            else reservationsInfo = new ArrayList<>();

            // DbConnector.changeUserPrivilege(a,b );
            request.setAttribute("reservationsInfo", reservationsInfo); // Store products in request scope.


            if (reservations == null || reservations.isEmpty() || reservationsInfo == null || reservationsInfo.size() == 0) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/reservation/manage-reservations.jsp?successConfirmation").forward(request, response);
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
