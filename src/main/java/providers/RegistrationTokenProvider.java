package providers;

import model.RegistrationToken;
import model.User;
import tools.UsefulFunctions;

import java.sql.*;
import java.util.UUID;

public class RegistrationTokenProvider {
    public String createRegistrationToken(final User user) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";


            int userId = user.getId();
            String token = UUID.randomUUID().toString();
            long timestamp = System.currentTimeMillis();

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO registration_token (value, user_id, timestamp) VALUES(?,?,?)");
            sql.setString(1, token);
            sql.setInt(2, userId);
            sql.setLong(3, timestamp);
            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.println("DB - Token successfully created.");
                return token;
            }

            return null;

        } catch (SQLException | ClassNotFoundException  e) {
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

    public RegistrationToken getRegistrationTokenByValue(final String token) {
        Connection conn = null;
        RegistrationToken resultToken = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT id, value, user_id, timestamp FROM registration_token WHERE value == ?");
            sql.setString(1, token);
            ResultSet rs = sql.executeQuery();

            resultToken = new RegistrationToken();
            while (rs.next()) {
                resultToken.setId(rs.getInt("id"));
                resultToken.setValue(rs.getString("value"));
                resultToken.setUserId(rs.getInt("user_id"));
                resultToken.setTimestamp(rs.getLong("timestamp"));
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
        return resultToken;
    }
}
