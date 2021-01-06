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
import java.util.List;

public class SearchController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        List<Hotel> hotelList = DbConnector.getAllHotels();
        request.setAttribute("hotelList", hotelList);

        if (hotelList.isEmpty()) {
            request.setAttribute("emptyList", "true");
        } else {
            request.setAttribute("emptyList", "false");
        }
        request.getRequestDispatcher("views/reservation/search.jsp").forward(request, response);
    }
}
