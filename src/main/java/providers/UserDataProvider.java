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
            System.out.println("XD2");
            String login        = user.getLogin();
            String pass         = UsefulFunctions.stringToMD5String(user.getPassword());
            String firstName    = user.getFirst_name();
            String lastName     = user.getLast_name();
            System.out.println("XD3");
            int privilege       = user.getUser_privilege() == null ? 1 : user.getUser_privilege().getValue();
            int email_confirmed = user.isEmail_confirmed() ? 1 : 0;
            long timestamp       = System.currentTimeMillis();


            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO user (login, password, user_privilege,first_name,last_name,email_confirmed,timestamp) VALUES(?,?,?,?,?,?,?)");
            sql.setString(1, login);
            sql.setString(2, pass);
            sql.setInt(3, privilege);
            sql.setString(4, firstName);
            sql.setString(5, lastName);
            sql.setInt(6, email_confirmed);
            sql.setLong(7, timestamp);
            int i = sql.executeUpdate();

            if (i > 0) {
                System.out.print("You are successfully registered...");
                System.out.println("DB - User succesfully added.");
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
            createUserObjectFromResult(user, sql);

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

    public User loadUserByEmail(String email) {
        Connection conn = null;
        User user = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT id, login, password, user_privilege, first_name, last_name, email_confirmed, timestamp " +
                            "FROM user " +
                            "WHERE user.login == ? ");


            sql.setString(1, email);

            user = new User();
            createUserObjectFromResult(user, sql);

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

    private void createUserObjectFromResult(User user, PreparedStatement sql) throws SQLException {
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
        return resultId;
    }

    public boolean setEmailConfirmed(final int userId) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE user\n" +
                            "SET email_confirmed = 1\n" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setInt(1, userId);
            sql.executeUpdate();

            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return  false;
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

    public void changeUserPrivilege(int id, Privilege privilege) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C://sqlite/db/database.sqlite";

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE user\n" +
                            "SET user_privilege = ?\n" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setInt(1, privilege.getValue());
            sql.setInt(2, id);
            sql.executeUpdate();

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
    }
}
