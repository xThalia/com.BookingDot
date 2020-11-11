package storage;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src.main.java.storage.bookingDB.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

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

    public static void createUserTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:src.main.java.storage.bookingDB.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS user (\n" +
    " username VARCHAR(16) NOT NULL, \n"+
  "email VARCHAR(255) NULL,\n" +
  "password VARCHAR(32) NOT NULL,\n" +
  "create_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP);\n";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Super, znowu sie udało");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        connect();
        createUserTable();
    }
}
