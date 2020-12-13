package providers;

import model.Hotel;
import model.Room;
import tools.UsefulFunctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomProvider {
    public boolean addRoom(Room room) { //required with hotel id
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";
            int capacity        = room.getCapacity();
            int price           = room.getPrice();
            long timestamp      = System.currentTimeMillis();
            int hotelId         = room.getHotelId();
            String picturePath = room.getPicturePath();
            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO room (capacity, price, timestamp, hotel_id, picture) VALUES(?,?,?,?,?)");
            sql.setInt(1, capacity);
            sql.setInt(2, price);
            sql.setLong(3, timestamp);
            sql.setInt(4, hotelId);
            sql.setString(5, picturePath);

            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.println("DB - Room succesfully added.");
                return true;
            }

            return false;

        } catch (SQLException | ClassNotFoundException  e) {
            System.out.println(e.getMessage());

            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public List<Room> getAllHotelRoom(int hotel_id) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            List <Room> hotelRooms = new ArrayList<>();
            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * FROM room " +
                            "WHERE " +
                            "hotel_id == ? ");


            sql.setInt(1, hotel_id);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setCapacity(rs.getInt("capacity"));
                room.setPrice(rs.getInt("price"));
                room.setTimestamp(rs.getInt("timestamp"));
                room.setPicturePath(rs.getString("picture"));
                room.setHotelId(rs.getInt("hotel_id"));
                hotelRooms.add(room);
            }
            return hotelRooms;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
