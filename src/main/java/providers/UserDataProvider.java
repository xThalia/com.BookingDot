package providers;

import enums.Privilege;
import model.User;
import tools.UsefulFunctions;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            final String encodedPassword = UsefulFunctions.stringToMD5String(pass);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO user (login, password, user_privilege,first_name,last_name,email_confirmed,timestamp) VALUES(?,?,?,?,?,?,?)");
            sql.setString(1, login);
            sql.setString(2, encodedPassword);
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

    public User loadUserById(int id) {
        Connection conn = null;
        User user = new User();
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT id, login, password, user_privilege, first_name, last_name, email_confirmed, timestamp " +
                         "FROM user " +
                         "WHERE user.id == ? ");


            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setUser_privilege(Privilege.getPrivilage(rs.getInt("user_privilege")));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail_confirmed(rs.getInt("email_confirmed") == 1);
                user.setTimestamp(rs.getInt("timestamp"));
            }

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
        return user;
    }

    public int authenticateUserWithLoginAndPassword(final String login, final String password) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            final String encodedPassword = UsefulFunctions.stringToMD5String(password);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT id FROM user WHERE user.login == ? AND user.password == ?");
            sql.setString(1, login);
            sql.setString(2, encodedPassword);
            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                resultId = rs.getInt("id");
            }

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
        }
        System.out.println(resultId);
        return resultId;
    }
}
