package providers;

import enums.Privilege;
import model.Comment;
import model.User;
import tools.BookingConstants;
import tools.UsefulFunctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentProvider {
    public boolean addComment(String text, int userId, int hotelId) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;
            long timestamp = System.currentTimeMillis();

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO comment (text, timestamp, hotel_id, user_id) VALUES(?,?,?,?)");
            sql.setString(1, text);
            sql.setLong(2, timestamp);
            sql.setInt(3, hotelId);
            sql.setInt(4, userId);

            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.println("DB - Comment has been added.");
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

    public List<Comment> getAllHotelComments(int hotelId) {
        Connection conn = null;

        try {
            List<Comment> allComments = new ArrayList<>();
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * " +
                            "FROM comment " +
                            "WHERE hotel_id == ? ");

            sql.setInt(1, hotelId);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setText(rs.getString("text"));
                comment.setTimestamp(rs.getInt("timestamp"));
                comment.setHotelId(rs.getInt("hotel_id"));
                comment.setUserId(rs.getInt("user_id"));

                allComments.add(comment);
            }

            if(allComments.size() == 0) return null;
            else return allComments;

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
