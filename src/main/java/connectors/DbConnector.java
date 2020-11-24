package connectors;

import enums.Privilege;
import model.User;
import providers.DbProvider;
import providers.RegistrationTokenProvider;
import providers.UserDataProvider;
import services.RegisterService;

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

    public static User loadUserByEmail(String email) {
        UserDataProvider userDataProvider = new UserDataProvider();
        return userDataProvider.loadUserByEmail(email);
    }

    public static String createRegistrationToken(User user) {
        RegistrationTokenProvider registrationTokenProvider = new RegistrationTokenProvider();
        return registrationTokenProvider.createRegistrationToken(user);
    }

    public static void changeUserPrivilege(int id, Privilege privilege) {
        UserDataProvider userDataProvider = new UserDataProvider();
        userDataProvider.changeUserPrivilege(id, privilege);
    }

    public static void main(String[] args) {
        // Rejestracja uzytkownika
         RegisterService registerService = new RegisterService();
         User user = new User("mitela24@gmail.com", "123456", Privilege.ORDINARY,"Adam","Kowalski", false, 1234);
       // registerService.registerUserAndSendToken(user);

        // Weryfikacja tokenu i zmiana potwierdzenia na potwierdzony
         int result = registerService.verifyUserToken(user.getLogin(), "4e0846be-14f3-4b54-8816-138dc5597a62");
         System.out.println(result);
        //addUser(user);
        // authenticateUser(user);
        // createDatabase();
    }
}
