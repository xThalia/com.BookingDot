package providers;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDataProvider {
    public void addUser() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C://sqlite/db/bookingDB.db";

            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO user (login, password, user_privilege,email_confirmed,timestamp)  \n" +
                    "VALUES (\"test\", \"test1\", 1,1,1234556);";

            stmt.execute(sql);

            System.out.println("DB - User succesfully added.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
