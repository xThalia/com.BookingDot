package connectors;

import enums.Privilege;
import model.User;
import providers.DbProvider;
import providers.UserDataProvider;

public class DbConnector {
    public static void createDatabase() {
        DbProvider dbProvider = new DbProvider();
        dbProvider.createDatabase();
    }

    public static void addUser(User user) {
        UserDataProvider userDataProvider = new UserDataProvider();
        userDataProvider.addUser(user);
    }

    public static void authenticateUser(User user) {
        UserDataProvider userDataProvider = new UserDataProvider();
        userDataProvider.authenticateUserWithLoginAndPassword(user.getLogin(), user.getPassword());
    }

    public static User loadUserById(int id) {
        UserDataProvider userDataProvider = new UserDataProvider();
        return userDataProvider.loadUserById(id);
    }

    public static void main(String[] args) {
        User user = new User("mai1232335@mail.com", "12345", Privilege.ORDINARY,"lol","lol", true, 1234);
        addUser(user);
        authenticateUser(user);
    }
}
