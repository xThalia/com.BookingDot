package model;

import enums.Privilege;

public class User {
    private int id;
    private String login;
    private String password;
    private Privilege user_privilege;
    private String first_name;
    private String last_name;
    private boolean email_confirmed;
    private long timestamp;

    public User() {
        this.id = 0;
        this.login = "";
        this.password = "";
        this.user_privilege = Privilege.ORDINARY;
        this.first_name = "";
        this.last_name = "";
        this.email_confirmed = false;
        this.timestamp = System.currentTimeMillis();
    }

    public User(String login, String password, Privilege user_privilege, boolean email_confirmed, int timestamp) {
        this.login = login;
        this.password = password;
        this.user_privilege = user_privilege;
        this.email_confirmed = email_confirmed;
        this.timestamp = timestamp;
    }

    public User(String login, String password, Privilege user_privilege, String first_name, String last_name, boolean email_confirmed, int timestamp) {
        this.login = login;
        this.password = password;
        this.user_privilege = user_privilege;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_confirmed = email_confirmed;
        this.timestamp = timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_privilege(Privilege user_privilege) {
        this.user_privilege = user_privilege;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail_confirmed(boolean email_confirmed) {
        this.email_confirmed = email_confirmed;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Privilege getUser_privilege() {
        return user_privilege;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public boolean isEmail_confirmed() {
        return email_confirmed;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
