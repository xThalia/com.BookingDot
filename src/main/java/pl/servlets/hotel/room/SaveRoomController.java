package pl.servlets.hotel.room;

import connectors.DbConnector;
import model.Hotel;
import model.Room;
import providers.HotelProvider;
import providers.RoomProvider;
import tools.UsefulFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/SaveRoomController")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class SaveRoomController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");

        int roomId            = Integer.parseInt(request.getParameter("roomId"));
        String name           = request.getParameter("name");
        int capacity          = Integer.parseInt(request.getParameter("capacity"));
        int price             = Integer.parseInt(request.getParameter("price"));
        int hotelId           = Integer.parseInt(request.getParameter("hotelId"));
        boolean deletePicture = Boolean.parseBoolean(request.getParameter("deletePicture"));


        if(session.getAttribute("currentSessionUser") != null ) {
            RoomProvider room = new RoomProvider();

            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath  = applicationPath + File.separator + AddRoomController.UPLOAD_DIR;

            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());

            String fileName;
            String uploadedFileName;

            for (Part part : request.getParts()) {
                fileName = AddRoomController.getFileName(part);

                if(UsefulFunctions.isImageFile(fileName)) {
                    uploadedFileName = UUID.randomUUID().toString();
                    part.write(uploadFilePath + File.separator + uploadedFileName + ".jpg");
                    request.setAttribute("message", uploadedFileName + " File uploaded successfully!");
                    System.out.println("NOWE ZDJ");
                    room.editRoomWithPicture(roomId, name, capacity, price, AddRoomController.UPLOAD_DIR + File.separator + uploadedFileName + ".jpg");
                } else {
                    if(deletePicture) {
                        room.editRoomWithPicture(roomId, name, capacity, price, "");
                    }
                    room.editRoom(roomId, name, capacity, price);
                }
            }

            List<Room> hotelRoomList = DbConnector.getAllHotelRooms(hotelId);
            int userId  = (int)session.getAttribute("currentSessionUser");
            request.setAttribute("hotelRoomList", hotelRoomList);
            request.setAttribute("hotel", DbConnector.getHotelByIdAndUserId(hotelId, userId));
            if (hotelRoomList == null) {
                request.setAttribute("emptyList", "true");
            } else {
                request.setAttribute("emptyList", "false");
            }
            request.getRequestDispatcher("views/hotel/edit-hotel.jsp?status=successEdit").forward(request, response); // Forward to JSP page to display them in a HTML table
        } else {
            response.sendRedirect("views/auth/login.jsp");
        }
    }
}
