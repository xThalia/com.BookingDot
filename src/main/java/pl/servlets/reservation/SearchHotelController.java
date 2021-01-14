package pl.servlets.reservation;

import connectors.DbConnector;
import model.Hotel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchHotelController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        List<Hotel> hotelList = DbConnector.getAllHotels();

        String City, startDate, endDate;
        City = request.getParameter("city");
        startDate = request.getParameter("datepicker");
        endDate = request.getParameter("datepicker2");

        if (City.equals("")) {
            hotelList = DbConnector.getAllHotels();
            if (hotelList == null || hotelList.isEmpty()) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("hotelList", hotelList);
            request.getRequestDispatcher("views/reservation/search.jsp").forward(request, response);
        } else {
            hotelList = DbConnector.getAllHotelsByCityAndReservationDate(City, startDate, endDate);

            if (hotelList == null || hotelList.isEmpty()) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("hotelList", hotelList);
            request.getRequestDispatcher("views/reservation/search.jsp").forward(request, response);
        }

    }
}
