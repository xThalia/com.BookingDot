package providers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbProvider {
    public void createDatabase() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);
            System.out.println("DB - Connection to SQLite has been established.");

            createUserTable(conn);
            createHotelTable(conn);
            createRoomTable(conn);
            createHotelUserTable(conn);
            createRegistrationTokenTable(conn);
            System.out.println("DB - Database schema succesfully created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    private void createUserTable(Connection conn) {
        String sql = "CREATE TABLE user (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  login TEXT NOT NULL,\n" +
                "  password TEXT NOT NULL,\n" +
                "  user_privilege INT NOT NULL,\n" +
                "  first_name TEXT,\n" +
                "  last_name TEXT,\n" +
                "  email_confirmed INT NOT NULL,\n" +
                "  timestamp INT NOT NULL,\n"+
                "CONSTRAINT user_login_unique UNIQUE (login))\n";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createHotelTable(Connection conn) {
        String sql = "CREATE TABLE hotel (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name TEXT NOT NULL,\n" +
                "  address TEXT,\n" +
                "  city TEXT NOT NULL,\n" +
                "  timestamp INT NOT NULL)\n";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createRoomTable(Connection conn) {
        String sql = "CREATE TABLE room (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name TEXT NOT NULL,\n" +
                "  capacity INT NOT NULL,\n" +
                "  price INT NOT NULL,\n" +
                "  timestamp INT NOT NULL,\n" +
                "  picture TEXT NOT NULL,\n" +
                "  hotel_id INT NOT NULL,\n" +
                "   FOREIGN KEY (hotel_id) \n" +
                "      REFERENCES hotel (id) \n" +
                "         ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION)\n";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private void createHotelUserTable(Connection conn) {
        String sql = "CREATE TABLE hotel_user(\n" +
                "  hotel_id INTEGER,\n" +
                "  user_id INTEGER,\n" +
                "  timestamp INT NOT NULL,\n" +
                "   PRIMARY KEY(hotel_id,user_id)"+
                "   FOREIGN KEY (hotel_id) \n" +
                "      REFERENCES hotel (id) \n" +
                "         ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION,\n" +
                "   FOREIGN KEY (user_id) \n" +
                "      REFERENCES user (id) \n" +
                "         ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION)\n";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createRegistrationTokenTable(Connection conn) {
        String sql = "CREATE TABLE registration_token (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  value TEXT NOT NULL,\n" +
                "  user_id INTEGER NOT NULL,\n" +
                "  timestamp INT NOT NULL,\n" +
                "   FOREIGN KEY (user_id) \n" +
                "      REFERENCES user (id) \n" +
                "         ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION)\n";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
