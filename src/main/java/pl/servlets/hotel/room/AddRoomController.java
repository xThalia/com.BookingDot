package pl.servlets.hotel.room;

import connectors.DbConnector;
import model.Hotel;
import model.Room;
import tools.UsefulFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
@WebServlet("/AddRoomController")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class AddRoomController extends HttpServlet {

    public static final String UPLOAD_DIR = "uploads";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(session.getAttribute("currentSessionUser") != null ) {
            int hotelId = Integer.parseInt(request.getParameter("hotel"));

            request.setAttribute("hotelId", hotelId);
            request.getRequestDispatcher("views/hotel/room/add-room.jsp").forward(request, response);
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String name  = request.getParameter("name");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        int price    = Integer.parseInt(request.getParameter("price"));
        int hotelId  = Integer.parseInt(request.getParameter("hotelId"));

        if(session.getAttribute("currentSessionUser") != null ) {
            int userId  = (int)session.getAttribute("currentSessionUser");

            Room room = new Room();

            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath  = applicationPath + File.separator + UPLOAD_DIR;

            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());

            String fileName;
            String uploadedFileName;

            for (Part part : request.getParts()) {
                fileName = getFileName(part);

                if(UsefulFunctions.isImageFile(fileName)) {
                    uploadedFileName = UUID.randomUUID().toString();
                    part.write(uploadFilePath + File.separator + uploadedFileName + ".jpg");
                    room.setPicturePath(UPLOAD_DIR + File.separator + uploadedFileName + ".jpg");

                    request.setAttribute("message", uploadedFileName + " File uploaded successfully!");
                } else {
                    request.setAttribute("message", "That's not an image file.");
                }
            }

            room.setName(name);
            room.setCapacity(capacity);
            room.setPrice(price);
            room.setHotelId(hotelId);
            DbConnector.addRoom(room);

            List<Room> hotelRoomList = DbConnector.getAllHotelRooms(hotelId);
            request.setAttribute("hotelRoomList", hotelRoomList);
            request.setAttribute("hotel", DbConnector.getHotelByIdAndUserId(hotelId, userId));
            if (hotelRoomList == null) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/edit-hotel.jsp?status=success").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }

    public static String getFileName(Part part)
    {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
