package providers;

import enums.Privilege;
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
            String name         = room.getName();
            int capacity        = room.getCapacity();
            int price           = room.getPrice();
            long timestamp      = System.currentTimeMillis();
            int hotelId         = room.getHotelId();
            String picturePath  = room.getPicturePath();
            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO room (name, capacity, price, timestamp, hotel_id, picture) VALUES(?,?,?,?,?,?)");
            sql.setString(1, name);
            sql.setInt(2, capacity);
            sql.setInt(3, price);
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
                room.setName(rs.getString("name"));
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

    public int changeUserImage(int userId, String picturePath) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE room\n" +
                            "SET picture = ?\n" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setString(1, picturePath);
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

    public int deleteRoom(int roomId) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "DELETE FROM room\n" +
                            "WHERE id = ? ");
            sql.setInt(1, roomId);

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

    public int editRoom(int roomId, String name, int capacity, int price) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE room\n" +
                            "SET name = ?,\n" +
                            "capacity = ?,\n" +
                            "price = ?,\n" +
                            "timestamp = ?" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setString(1, name);
            sql.setInt(2, capacity);
            sql.setInt(3, price);
            sql.setInt(4, (int) System.currentTimeMillis());
            sql.setInt(5, roomId);

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

    public int editRoomWithPicture(int roomId, String name, int capacity, int price, String picturePath) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE room\n" +
                            "SET name = ?,\n" +
                            "capacity = ?,\n" +
                            "price = ?,\n" +
                            "timestamp = ?,\n" +
                            "picture = ?" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setString(1, name);
            sql.setInt(2, capacity);
            sql.setInt(3, price);
            sql.setInt(4, (int) System.currentTimeMillis());
            sql.setString(5, picturePath);

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
