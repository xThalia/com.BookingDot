package pl.servlets.reservation;

import connectors.DbConnector;
import model.Reservation;
import model.ReservationInfoToShow;
import model.User;
import providers.DbProvider;
import providers.UserDataProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageReservationsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(session.getAttribute("currentSessionUser") != null ) {
          //  int a= 0; int b= 0;

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
            request.getRequestDispatcher("views/reservation/manage-reservations.jsp").forward(request, response);
        }

        else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login    = request.getParameter("email");
        String password = request.getParameter("password");
        int id          = 0;
        UserDataProvider userDataProvider = new UserDataProvider();

        System.out.println(login + " password " + password);
        System.out.println(userDataProvider.authenticateUserWithLoginAndPassword(login, password));
        if (userDataProvider.authenticateUserWithLoginAndPassword(login, password) != 0) {
            id = userDataProvider.authenticateUserWithLoginAndPassword(login, password);
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser",id);
            response.sendRedirect("views/home.jsp");
        } else {
            response.sendRedirect("views/auth/login.jsp?status=error");
        }
    }
}
