package providers;

import enums.Privilege;
import model.User;
import tools.BookingConstants;
import tools.UsefulFunctions;

import java.sql.*;

public class OfferProvider {
    public boolean addOffer(int userId) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            long timestamp = System.currentTimeMillis();

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO offer (timestamp, user_id) VALUES(?,?)");
            sql.setLong(1, timestamp);
            sql.setInt(2, userId);

            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.println("DB - Offer data for user has been created");
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

    public int getTimestampByUserId(int id) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT timestamp " +
                            "FROM offer " +
                            "WHERE user_id == ? ");


            sql.setInt(1, id);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                return rs.getInt("timestamp");
            }
            return 0;
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
    }

    public int updateOfferTimestamp(int userId) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            long timestamp = System.currentTimeMillis();

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE offer\n" +
                            "SET timestamp = ?\n" +
                            "WHERE\n" +
                            "    user_id == ? ");

            sql.setLong(1, timestamp);
            sql.setInt(2, userId);

            return sql.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return 0;
        }
    }
}
