package providers;

import model.Hotel;
import tools.BookingConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelUserProvider {
    public boolean addHotelUser(int hotelId, int userId) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;
            long timestamp = System.currentTimeMillis();

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO hotel_user (hotel_id, user_id, timestamp) VALUES(?,?,?)");
            sql.setInt(1, hotelId);
            sql.setInt(2, userId);
            sql.setLong(3, timestamp);

            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.println("DB - HotelUser succesfully added.");
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

    public int getHotelIdByUserId(int id) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT hotel_id " +
                            "FROM hotel_user " +
                            "WHERE user_id == ?");


            sql.setInt(1, id);


            ResultSet rs = sql.executeQuery();

            if (rs.next()) return rs.getInt("id");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

            return 0;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return 0;
    }

    public int getHotelIdByUserIdAndHotelName(String name, int userId) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT hotel_user.hotel_id FROM hotel_user, hotel " +
                            "WHERE " +
                            "hotel_user.user_id == ? " +
                            "AND hotel_user.hotel_id == hotel.id AND hotel.name == ?");


            sql.setInt(1, userId);
            sql.setString( 2, name.replaceAll("'", ""));

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                return rs.getInt("hotel_id");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

            return 0;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return 0;
    }
}
