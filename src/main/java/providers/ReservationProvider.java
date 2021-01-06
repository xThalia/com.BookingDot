package providers;

import model.Reservation;
import model.Room;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationProvider {
    public boolean addReservation(int roomId, int userId, String startDate, String endDate, boolean isConfirmed) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";
            conn = DriverManager.getConnection(url);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date startDateConverted = format.parse(startDate);
            Date endDateConverted = format.parse(endDate);
            Timestamp startDateTimestamp = new Timestamp(startDateConverted.getTime());
            Timestamp endDateTimestamp = new Timestamp(endDateConverted.getTime());


            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO reservation (room_id, user_id, start_date, end_date, registration_confirmed) VALUES(?,?,?,?,?)");
            sql.setInt(1, roomId);
            sql.setInt(2, userId);
            sql.setLong(3, startDateTimestamp.getTime());
            sql.setLong(4, endDateTimestamp.getTime());
            sql.setBoolean(5, isConfirmed);

            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.println("DB - Reservation successfully added");
                return true;
            }

            return false;

        } catch (SQLException | ClassNotFoundException | ParseException e) {
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

    public void editReservation(){

    }

    public void deleteReservation() {

    }
}
