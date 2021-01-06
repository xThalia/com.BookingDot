package providers;

import model.Reservation;
import model.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationProvider {
    public void addReservation(Reservation reservation) { //required with hotel id
    /*    Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";
            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO reservation (room_id, user_id, start_date, end_date) VALUES(?,?,?,?,?,?)");
            sql.setString(1, reservation.getRoomId());
            sql.setInt(2, reservation.getUserId());
          //  sql.setInt(3, reservation.get);
            sql.setLong(4, timestamp);
            sql.setInt(5, hotelId);
            sql.setString(6, picturePath);

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
        } */
    }

    public void editReservation(){

    }

    public void deleteReservation() {

    }
}
