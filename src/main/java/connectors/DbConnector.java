package connectors;

import model.User;
import providers.DbProvider;
import providers.UserDataProvider;

public class DbConnector {
    public static void createDatabase() {
        DbProvider dbProvider = new DbProvider();
        dbProvider.createDatabase();
    }

    public static void addUser(User user) { //ja to jeszcze pozniej ladniej zrobie, zeby tu mozna bylo dawac obiekt Usera po prostu
        UserDataProvider userDataProvider = new UserDataProvider();
        userDataProvider.addUser(user);

    }

    public static void main(String[] args) {
        createDatabase();
//        addUser();
    }
}
