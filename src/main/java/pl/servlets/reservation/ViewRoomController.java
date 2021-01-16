package pl.servlets.reservation;

import connectors.DbConnector;
import model.Comment;
import model.Hotel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ViewRoomController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Hotel hotel = DbConnector.getHotelById(hotelId);

        long lengthOfStay;
        try {
            Date startDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Date endDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            lengthOfStay = TimeUnit.DAYS.convert(endDateObject.getTime() - startDateObject.getTime(), TimeUnit.MILLISECONDS);
        }
        catch (ParseException e) {
            lengthOfStay = 0;
        }



        if(session.getAttribute("currentSessionUser") != null ) {
            request.setAttribute("room", DbConnector.getRoomByIdAndHotelId(roomId, hotelId));
            request.setAttribute("hotelId", hotelId);
            request.setAttribute("roomId", roomId);
            request.setAttribute("hotel", hotel);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("lengthOfStay", lengthOfStay);

            request.getRequestDispatcher("views/reservation/view-room.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
