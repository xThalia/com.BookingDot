package providers;

import enums.Privilege;
import model.Hotel;
import model.Room;
import tools.BookingConstants;
import tools.UsefulFunctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomProvider {
    public boolean addRoom(Room room) { //required with hotel id
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;
            String name         = room.getName().replaceAll("'", "");;
            int capacity        = room.getCapacity();
            int price           = room.getPrice();
            long timestamp      = System.currentTimeMillis();
            int hotelId         = room.getHotelId();
            String picturePath  = room.getPicturePath().replaceAll("'", "");;
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

    public Room getRoomById(int id) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            Room room = new Room();
            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * FROM room " +
                            "WHERE " +
                            "id == ? ");


            sql.setInt(1, id);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setCapacity(rs.getInt("capacity"));
                room.setPrice(rs.getInt("price"));
                room.setTimestamp(rs.getInt("timestamp"));
                room.setPicturePath(rs.getString("picture"));
                room.setHotelId(rs.getInt("hotel_id"));
            }

            if(room.getId() == 0) return null;
            else return room;

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

    public List<Room> getAllHotelRoom(int hotel_id) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

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

    public List<Hotel> getHotelWithRoomsByCity(String city) {
        HotelProvider hotelProvider = new HotelProvider();
        RoomProvider roomProvider = new RoomProvider();
        List<Hotel> hotels = hotelProvider.getAllHotelsByCity(city);
        if(hotels == null) return null;
        for (Hotel hotel : hotels) {
            List<Room> rooms = roomProvider.getAllHotelRoom(hotel.getId());
            hotel.setHotelRooms(rooms);
        }
        return hotels;
    }

    public Hotel getHotelWithRoomsById(int id) {
        HotelProvider hotelProvider = new HotelProvider();
        RoomProvider roomProvider = new RoomProvider();
        Hotel hotel = hotelProvider.getHotelById(id);
        if(hotel == null) return null;

        List<Room> rooms = roomProvider.getAllHotelRoom(hotel.getId());
        hotel.setHotelRooms(rooms);

        return hotel;
    }

    public int changeUserImage(int userId, String picturePath) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE room\n" +
                            "SET picture = ?\n" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setString(1, picturePath.replaceAll("'", ""));
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
            String url = BookingConstants.databaseUrl;

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
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE room\n" +
                            "SET name = ?,\n" +
                            "capacity = ?,\n" +
                            "price = ?,\n" +
                            "timestamp = ?" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setString(1, name.replaceAll("'", ""));
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
            String url = BookingConstants.databaseUrl;

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
            sql.setString(1, name.replaceAll("'", ""));
            sql.setInt(2, capacity);
            sql.setInt(3, price);
            sql.setInt(4, (int) System.currentTimeMillis());
            sql.setString(5, picturePath.replaceAll("'", ""));
            sql.setInt(6, roomId);


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
