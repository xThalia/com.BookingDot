package providers;

import model.Reservation;
import model.Room;
import tools.BookingConstants;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationProvider {
    public boolean addReservation(int roomId, int userId, String startDate, String endDate, int isConfirmed, boolean isFinished) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;
            conn = DriverManager.getConnection(url);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date startDateConverted = format.parse(startDate);
            Date endDateConverted = format.parse(endDate);
            Timestamp startDateTimestamp = new Timestamp(startDateConverted.getTime());
            Timestamp endDateTimestamp = new Timestamp(endDateConverted.getTime());


            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO reservation (room_id, user_id, start_date, end_date, reservation_confirmed, reservation_finished) VALUES(?,?,?,?,?,?)");
            sql.setInt(1, roomId);
            sql.setInt(2, userId);
            sql.setLong(3, startDateTimestamp.getTime());
            sql.setLong(4, endDateTimestamp.getTime());
            sql.setInt(5, isConfirmed);
            sql.setBoolean(6, isFinished);

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

    public int updateReservationWithConfirmation(int reservationId, int isAccepted) { //0 - niepotwierdzona 1 - zaakceptowana 2 - odrzucona
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE reservation\n" +
                            "SET reservation_confirmed = ?" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setInt(1, isAccepted);
            sql.setInt(2, reservationId);

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

    public boolean checkReservationForRoomBetweenDate(int roomId, Timestamp startDate, Timestamp endDate) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;
            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * FROM reservation " +
                            "WHERE (room_id == ?) AND" +
                            "((start_date > ? AND end_date < ?) OR " +
                            "(start_date > ? AND start_date < ?) OR " +
                            "(end_date > ? AND end_date < ?) OR " +
                            "(start_date < ? AND end_date > ?))");
            sql.setInt(1, roomId);
            sql.setLong(2, startDate.getTime());
            sql.setLong(3, endDate.getTime());
            sql.setLong(4, startDate.getTime());
            sql.setLong(5, endDate.getTime());
            sql.setLong(6, startDate.getTime());
            sql.setLong(7, endDate.getTime());
            sql.setLong(8, startDate.getTime());
            sql.setLong(9, endDate.getTime());

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                int r = rs.getInt("id");
                return false; //kiedy zwroci rezerwacje w danym terminie
            }
            return true; //kiedy pokoj jest wolny w danym terminie

        } catch (SQLException | ClassNotFoundException e) {
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

    public List<Reservation> getCurrentAndUpcomingReservationsForRoom(int roomId) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;
            conn = DriverManager.getConnection(url);
            List <Reservation> reservations = new ArrayList();

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * FROM reservation " +
                            "WHERE (room_id == ?) AND" +
                            "(end_date > ?)");
            sql.setInt(1, roomId);
            sql.setLong(2, System.currentTimeMillis());

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setUserId(rs.getInt("user_id"));
                reservation.setStartDate(rs.getInt("start_date"));
                reservation.setEndDate(rs.getInt("end_date"));
                reservation.setReservationConfirmed(rs.getBoolean("reservation_confirmed"));
                reservation.setReservationFinished(rs.getBoolean("reservation_finished"));
                reservations.add(reservation);
            }

            if(reservations.size() == 0) return null;
            else return reservations;

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
