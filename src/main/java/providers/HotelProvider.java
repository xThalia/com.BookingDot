package providers;

import enums.Privilege;
import model.Hotel;
import model.User;
import tools.UsefulFunctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelProvider {

    public boolean addHotel(Hotel hotel) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";
            String name    = hotel.getName();
            String address = hotel.getAddress();
            String city    = hotel.getCity();
            long timestamp = System.currentTimeMillis();


            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO hotel (name, address, city, timestamp) VALUES(?,?,?,?)");
            sql.setString(1, name);
            sql.setString(2, address);
            sql.setString(3, city);
            sql.setLong(4, timestamp);

            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.println("DB - Hotel succesfully added.");
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

    public int getHotelIdByHotelData(Hotel hotel) {
            Connection conn = null;
            try {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

                conn = DriverManager.getConnection(url);

                PreparedStatement sql = conn.prepareStatement(
                        "SELECT id FROM hotel " +
                                "WHERE " +
                                    "hotel.name == ? " +
                                    "AND hotel.address == ? " +
                                    "AND hotel.city == ?");


                sql.setString(1, hotel.getName());
                sql.setString( 2, hotel.getAddress());
                sql.setString(3, hotel.getCity());

                ResultSet rs = sql.executeQuery();

                while (rs.next()) {
                    return rs.getInt("id");
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

    public List<Hotel> getAllHotels() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            List <Hotel> allHotels = new ArrayList<>();
            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * FROM hotel");

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setAddress(rs.getString("address"));
                hotel.setCity(rs.getString("city"));
                hotel.setTimestamp(rs.getInt("timestamp"));
                allHotels.add(hotel);
            }
            return allHotels;

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

    public List<Hotel> getAllUserHotel(int userId) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            List <Hotel> userHotels = new ArrayList<>();
            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * FROM hotel, hotel_user " +
                            "WHERE " +
                            "hotel_user.user_id == ? " +
                            "AND hotel_user.hotel_id == hotel.id");


            sql.setInt(1, userId);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setAddress(rs.getString("address"));
                hotel.setCity(rs.getString("city"));
                hotel.setTimestamp(rs.getInt("timestamp"));
                userHotels.add(hotel);
            }
            return userHotels;

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

    public int deleteHotel(int hotelId) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "DELETE FROM hotel\n" +
                            "WHERE id = ? ");
            sql.setInt(1, hotelId);

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

    public int editHotel(int hotelId, String name, String address, String city) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE hotel\n" +
                            "SET name = ?,\n" +
                            "address = ?,\n" +
                            "city = ?,\n" +
                            "timestamp = ?" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setString(1, name);
            sql.setString(2, address);
            sql.setString(3, city);
            sql.setInt(4, (int) System.currentTimeMillis());
            sql.setInt(5, hotelId);

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
