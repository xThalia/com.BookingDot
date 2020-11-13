package connectors;

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

    public static void main(String[] args) {
        createDatabase();
    }
}
