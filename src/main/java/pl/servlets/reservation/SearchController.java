package pl.servlets.reservation;

import connectors.DbConnector;
import model.Hotel;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class SearchController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        List<Hotel> hotelList = DbConnector.getAllHotels();
        String startDate, endDate;
        request.setAttribute("hotelList", hotelList);

        if (hotelList.isEmpty()) {
            request.setAttribute("emptyList", "true");
        } else {
            request.setAttribute("emptyList", "false");
        }
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(Calendar.getInstance().getTime());
        cal.add(Calendar.DATE, 1);
        endDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.getRequestDispatcher("views/reservation/search.jsp").forward(request, response);
    }
}
