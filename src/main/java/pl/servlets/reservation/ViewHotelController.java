package pl.servlets.reservation;

import connectors.DbConnector;
import model.Comment;
import model.Hotel;
import model.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ViewHotelController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        List<Room> hotelRoomList;
        int hotelId;
        String startDate, endDate;
        Hotel hotel;

        hotelId = Integer.parseInt(request.getParameter("hotelId"));
        hotel = DbConnector.getHotelById(hotelId);
        request.setAttribute("hotel", hotel);

        startDate = request.getParameter("startDate");
        endDate = request.getParameter("endDate");

        hotelRoomList = DbConnector.getFreeRoomsByHotelId(hotelId, startDate, endDate);
        List<Comment> comments = DbConnector.getAllHotelComments(hotelId);

        long lengthOfStay;
        try {
            Date startDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Date endDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            lengthOfStay = TimeUnit.DAYS.convert(endDateObject.getTime() - startDateObject.getTime(), TimeUnit.MILLISECONDS);
        }
        catch (ParseException e) {
            lengthOfStay = 0;
        }

        request.setAttribute("hotelRoomList", hotelRoomList);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("comments", comments);
        request.setAttribute("lengthOfStay", lengthOfStay);

        System.out.println(comments);
        System.out.println(hotelId);
        if (hotelRoomList == null || hotelRoomList.isEmpty()) {
            request.setAttribute("emptyList", "true");
        } else {
            request.setAttribute("emptyList", "false");
        }
        if (comments == null || comments.isEmpty()) {
            request.setAttribute("emptyCommentList", "true");
        } else {
            request.setAttribute("emptyCommentList", "false");
        }
        request.getRequestDispatcher("views/reservation/view-hotel.jsp").forward(request, response);
    }
}
