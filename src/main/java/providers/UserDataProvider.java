package providers;

import model.User;

import java.sql.*;

public class UserDataProvider {
    public boolean addUser(User user) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            String login        = user.getLogin();
            String pass         = user.getPassword();
            String firstName    = user.getFirst_name();
            String lastName     = user.getLast_name();
            int privilege       = 1;
            int email_confirmed = 1;
            int timestamp       = 1230;

            conn = DriverManager.getConnection(url);
//            Statement stmt = conn.createStatement();
//
//            String sql = "INSERT INTO user (login, password, user_privilege,email_confirmed,timestamp)  \n" +
//                    "VALUES ("+ user.getLogin() + "," + user.getPassword() + ", 1,1,1234556 + );";
//
//            stmt.execute(sql);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO user (login, password, user_privilege,first_name,last_name,email_confirmed,timestamp) VALUES(?,?,?,?,?,?,?)");
            sql.setString(1, login);
            sql.setString(2, pass);
            sql.setInt(3, privilege);
            sql.setString(4, firstName);
            sql.setString(5, lastName);
            sql.setInt(6, email_confirmed);
            sql.setInt(7, timestamp);
            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.print("You are successfully registered...");
                System.out.println("DB - User succesfully added.");
                return true;
            }

            return false;

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
}
