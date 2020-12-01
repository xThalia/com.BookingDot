package providers;

import model.Room;
import tools.UsefulFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO room (capacity, price, timestamp, hotel_id) VALUES(?,?,?,?)");
            sql.setInt(1, capacity);
            sql.setInt(2, price);
            sql.setLong(3, timestamp);
            sql.setInt(4, hotelId);

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
}
