package providers;

import enums.Privilege;
import model.User;
import tools.BookingConstants;
import tools.UsefulFunctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataProvider {
    public boolean addUser(User user) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            String login        = user.getLogin().replaceAll("'", "");
            String pass         = UsefulFunctions.stringToMD5String(user.getPassword()).replaceAll("'", "");;
            String firstName    = user.getFirstName().replaceAll("'", "");;
            String lastName     = user.getLastName().replaceAll("'", "");;
            int privilege       = user.getUserPrivilege() == null ? 1 : user.getUserPrivilege().getValue();
            int email_confirmed = user.isEmailConfirmed() ? 1 : 0;
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

    public List<User> loadAllUser() {
        Connection conn = null;

        try {
            List<User> allUser = new ArrayList<>();
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * " +
                            "FROM user ");


            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setUserPrivilege(Privilege.getPrivilage(rs.getInt("user_privilege")));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmailConfirmed(rs.getInt("email_confirmed") == 1);
                user.setTimestamp(rs.getInt("timestamp"));
                allUser.add(user);
            }

            return allUser;

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

    public User loadUserById(int id) {
        Connection conn = null;
        User user = new User();
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

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
            String url = BookingConstants.databaseUrl;

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
            user.setUserPrivilege(Privilege.getPrivilage(rs.getInt("user_privilege")));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmailConfirmed(rs.getInt("email_confirmed") == 1);
            user.setTimestamp(rs.getInt("timestamp"));
        }
    }
    //String url = BookingConstants.databaseUrl;

    public int authenticateUserWithLoginAndPassword(final String login, final String password) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            final String encodedPassword = UsefulFunctions.stringToMD5String(password);

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT id FROM user WHERE user.login == ? AND user.password == ?");
            sql.setString(1, login.replaceAll("'", ""));
            sql.setString(2, encodedPassword.replaceAll("'", ""));
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
            String url = BookingConstants.databaseUrl;

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

    public void changeUserPrivilege(int id, int privilege) {
        Connection conn = null;
        int resultId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = BookingConstants.databaseUrl;

            conn = DriverManager.getConnection(url);

            PreparedStatement sql = conn.prepareStatement(
                    "UPDATE user\n" +
                            "SET user_privilege = ?\n" +
                            "WHERE\n" +
                            "    id == ? ");
            sql.setInt(1, privilege);
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
