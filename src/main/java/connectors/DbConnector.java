package connectors;

import enums.Privilege;
import model.Hotel;
import model.Room;
import model.User;
import providers.*;
import services.RegisterService;

public class DbConnector {
    public static void createDatabase() {
        DbProvider dbProvider = new DbProvider();
        dbProvider.createDatabase();
        createAdminAccount();
    }

    public static void createAdminAccount() {
        final User user = new User("admin@admin","adminadmin", Privilege.ADMIN, true, System.currentTimeMillis());
        addUser(user);
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

    public static void addHotel(Hotel hotel, int user_id) {
        HotelProvider hotelProvider = new HotelProvider();
        HotelUserProvider hotelUserProvider = new HotelUserProvider();

        hotelProvider.addHotel(hotel);
        int hotel_id = hotelProvider.getHotelIdByHotelData(hotel);

        if (hotel_id != 0) {
            hotelUserProvider.addHotelUser(hotel_id, user_id);
        }
    }

    public static void addRoom(Room room, int hotel_id) {
        RoomProvider roomProvider = new RoomProvider();
        room.setHotelId(hotel_id);

        roomProvider.addRoom(room);
    }

    public static void main(String[] args) {
        // Rejestracja uzytkownika
      //   RegisterService registerService = new RegisterService();
    //     User user = new User("mitela24@gmail.com", "123456", Privilege.ORDINARY,"Adam","Kowalski", false, 1234);
       // registerService.registerUserAndSendToken(user);

        // Weryfikacja tokenu i zmiana potwierdzenia na potwierdzony
       //  int result = registerService.verifyUserToken(user.getLogin(), "4e0846be-14f3-4b54-8816-138dc5597a62");
       //  System.out.println(result);

        //addUser(user);
        // authenticateUser(user);
       //  createDatabase();

         User user = new User("login", "password", Privilege.ORDINARY, true, System.currentTimeMillis());
         Hotel hotel = new Hotel("nazwa", "mickiewicza", "ZG", System.currentTimeMillis());
    //     addUser(user);
         addHotel(hotel, user.getId());
    }
}
