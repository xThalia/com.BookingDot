package model;

import enums.Privilege;

public class User {
    private int id;
    private String login;
    private String password;
    private Privilege userPrivilege;
    private String firstName;
    private String lastName;
    private boolean emailConfirmed;
    private long timestamp;

    public User() {
        this.id = 0;
        this.login = "";
        this.password = "";
        this.userPrivilege = Privilege.ORDINARY;
        this.firstName = "";
        this.lastName = "";
        this.emailConfirmed = false;
        this.timestamp = System.currentTimeMillis();
    }

    public User(String login, String password, Privilege userPrivilege, boolean emailConfirmed, long timestamp) {
        this.login = login;
        this.password = password;
        this.userPrivilege = userPrivilege;
        this.emailConfirmed = emailConfirmed;
        this.timestamp = timestamp;
    }

    public User(String login, String password, Privilege userPrivilege, String firstName, String lastName, boolean emailConfirmed, long timestamp) {
        this.login = login;
        this.password = password;
        this.userPrivilege = userPrivilege;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailConfirmed = emailConfirmed;
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

    public void setUserPrivilege(Privilege userPrivilege) {
        this.userPrivilege = userPrivilege;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
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

    public Privilege getUserPrivilege() {
        return userPrivilege;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userPrivilege=" + userPrivilege +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailConfirmed=" + emailConfirmed +
                ", timestamp=" + timestamp +
                '}';
    }
}
