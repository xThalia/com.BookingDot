package connectors;

import enums.Privilege;
import model.Hotel;
import model.Room;
import model.User;
import providers.*;
import services.RegisterService;
import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbConnector {
    public static void createDatabase() {
        DbProvider dbProvider = new DbProvider();
        dbProvider.createDatabase();
        createAdminAccount();
        createRecepionistAccount();
        createOwnerAccount();
    }

    public static void createAdminAccount() {
        final User user = new User("admin@admin","adminadmin", Privilege.ADMIN,"admin","admin", true, System.currentTimeMillis());
        addUser(user);
    }

    public static void createRecepionistAccount() {
        final User user = new User("rec@rec","receptionist", Privilege.RECEPTIONIST,"receptionist","receptionist", true, System.currentTimeMillis());
        addUser(user);
    }

    public static void createOwnerAccount() {
        final User user = new User("owner@owner","ownerowner", Privilege.OWNER,"owner","owner", true, System.currentTimeMillis());
        addUser(user);
    }

    public static boolean addUser(User user) {
        UserDataProvider userDataProvider = new UserDataProvider();
        return userDataProvider.addUser(user);
    }

    public static String createRegistrationToken(User user) {
        RegistrationTokenProvider registrationTokenProvider = new RegistrationTokenProvider();
        return registrationTokenProvider.createRegistrationToken(user);
    }

    public static void authenticateUser(User user) {
        UserDataProvider userDataProvider = new UserDataProvider();
        userDataProvider.authenticateUserWithLoginAndPassword(user.getLogin(), user.getPassword());
    }

    public static List<User> getAllUser() {
        UserDataProvider userDataProvider = new UserDataProvider();
        return userDataProvider.loadAllUser();
    }

    public static User loadUserById(int id) {
        UserDataProvider userDataProvider = new UserDataProvider();
        return userDataProvider.loadUserById(id);
    }

    public static User loadUserByEmail(String email) {
        UserDataProvider userDataProvider = new UserDataProvider();
        return userDataProvider.loadUserByEmail(email);
    }

    public static void changeUserPrivilege(int id, int privilege) {
        UserDataProvider userDataProvider = new UserDataProvider();
        userDataProvider.changeUserPrivilege(id, privilege);
    }

    public static void addHotel(Hotel hotel, int user_id) {
        HotelProvider hotelProvider = new HotelProvider();
        HotelUserProvider hotelUserProvider = new HotelUserProvider();
        System.out.println("1");

        hotelProvider.addHotel(hotel);
        int hotel_id = hotelProvider.getHotelIdByHotelData(hotel);
        System.out.println("2");
        if (hotel_id != 0) {
            hotelUserProvider.addHotelUser(hotel_id, user_id);
            System.out.println("4");
        }
        System.out.println("3");
    }

    public static List<Hotel> getAllHotels() {
        HotelProvider provider = new HotelProvider();
        return provider.getAllHotels();
    }

    public static List<Hotel> getAllUserHotel(int userId) {
        HotelProvider provider = new HotelProvider();
        return provider.getAllUserHotel(userId);
    }

    public static Hotel getHotelByIdAndUserId(int hotelId, int userId) {
        List<Hotel> hotelList = DbConnector.getAllUserHotel(userId);

        for (Hotel hotel:hotelList) {
            if(hotel.getId() == hotelId) {
                return hotel;
            }
        }
        return null;
    }

    public static int getHotelByHotelNameAndUserId(String name, int userId) {
        HotelUserProvider provider = new HotelUserProvider();
        return  provider.getHotelIdByUserIdAndHotelName(name, userId);
    }

    public static void addRoom(Room room) {
        RoomProvider roomProvider = new RoomProvider();
        roomProvider.addRoom(room);
    }

    public static List<Room> getAllHotelRooms(int hotelId) {
        RoomProvider roomProvider = new RoomProvider();
        return  roomProvider.getAllHotelRoom(hotelId);
    }

    public static Room getRoomByIdAndHotelId(int roomId, int hotelId) {
        List<Room> roomList = DbConnector.getAllHotelRooms(hotelId);

        for (Room room:roomList) {
            if(room.getId() == roomId) {
                return room;
            }
        }
        return null;
    }

    public static boolean addReservation(int roomId, int userId, String startDate, String endDate, boolean isConfirmed) {
        ReservationProvider provider = new ReservationProvider();
        return provider.addReservation(roomId, userId, startDate, endDate, isConfirmed);
    }

    public static List<Hotel> getAllHotelsByCityAndReservationDate(String city, String startDate, String endDate) {
        RoomProvider roomProvider = new RoomProvider();
        ReservationProvider reservationProvider = new ReservationProvider();
        List<Hotel> hotelsInCity = roomProvider.getHotelWithRoomsByCity(city);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
        Date startDateConverted = format.parse(startDate);
        Date endDateConverted = format.parse(endDate);
        Timestamp startDateTimestamp = new Timestamp(startDateConverted.getTime());
        Timestamp endDateTimestamp = new Timestamp(endDateConverted.getTime());
        List<Hotel> hotelFreeAtDate = new ArrayList<>();

            for (Hotel hotel: hotelsInCity){
                boolean result;
                if(hotel.getHotelRooms() != null) {
                    result = false;
                    for (Room room : hotel.getHotelRooms()) {
                        if(reservationProvider.checkReservationForRoomBetweenDate(room.getId(), startDateTimestamp, endDateTimestamp))  {
                            result = true;
                            break;
                        }
                    }
                    if(result) hotelFreeAtDate.add(hotel);
                }
            }

        if(hotelFreeAtDate.size() == 0) return null;
        else return hotelFreeAtDate;

        } catch (ParseException e) {
            System.out.println("Wrong date format");
            return null;
        }

    }
    public static void main(String[] args) {
       // DbConnector.createDatabase();
  //     addReservation(2, 3, "2021-02-05", "2021-02-10", false);

   /*     getAllHotelsByCityAndReservationDate("Zakopane", "2021-02-02", "2021-02-06");
        getAllHotelsByCityAndReservationDate("Zakopane", "2021-02-06", "2021-02-11");
        getAllHotelsByCityAndReservationDate("Zakopane", "2021-02-02", "2021-02-11");
        getAllHotelsByCityAndReservationDate("Zakopane", "2021-02-06", "2021-02-09");
        getAllHotelsByCityAndReservationDate("Zakopane", "2021-02-10", "2021-02-15");
        getAllHotelsByCityAndReservationDate("Zakopane", "2021-02-15", "2021-02-20"); */
    //    addReservation(1, 3, "2021-01-06", "2021-01-07", false);
    //    addReservation(1, 3, "2021-01-06", "2021-01-07", false);
    //    addReservation(1, 3, "2021-01-06", "2021-01-07", false);
       // addReservation(1,1, "2019-06-01", "2019-06-01", false);
        // Rejestracja uzytkownika
    /*    RegisterService registerService = new RegisterService();
        User user = new User("mitela25@gmail.com", "123456", Privilege.ORDINARY,"Adam","Kowalski", true, 1234);
        registerService.registerUserAndSendToken(user); */

        // Weryfikacja tokenu i zmiana potwierdzenia na potwierdzony
        //  int result = registerService.verifyUserToken(user.getLogin(), "4e0846be-14f3-4b54-8816-138dc5597a62");
        // System.out.println(System.getProperty("catalina.base"));
        // addUser(user);
        // authenticateUser(user);
        //  createDatabase();
        //    User user = new User("login", "password", Privilege.ORDINARY, true, System.currentTimeMillis());
        //Hotel hotel = new Hotel("zielony dom", "mickiewicza", "ZG", System.currentTimeMillis());
        //Hotel hotel2 = new Hotel("zielony dom", "mickiewicza", "ZG", System.currentTimeMillis());
        //Hotel hotel3 = new Hotel("zielony dom", "mickiewicza", "ZG", System.currentTimeMillis());
        //     addUser(user);
        //addHotel(hotel, user.getId());

        //TEST DODAWANIA I ŁADOWANIA WSZYSTKICH HOTELI USERA
    /*   RegisterService registerService = new RegisterService();
        User user = new User("msdirer@gmail.com", "123456", Privilege.ORDINARY,"Adam","Kowalski", true, 1234);
        registerService.registerUserAndSendToken(user);

        int userId = loadUserByEmail(user.getLogin()).getId();
        user.setId(userId);

        Hotel hotel = new Hotel("zielony dom", "mickiewicza", "ZG", System.currentTimeMillis());
        Hotel hotel2 = new Hotel("zolty dom", "mickiewicza", "ZG", System.currentTimeMillis());
        Hotel hotel3 = new Hotel("rozowy dom", "mickiewicza", "ZG", System.currentTimeMillis());

        addHotel(hotel, user.getId());
        addHotel(hotel2, user.getId());
        addHotel(hotel3, user.getId());

        List <Hotel> hotels = getAllUserHotel(user.getId());

        for (Hotel h: hotels) {
            System.out.println(h.toString());
        } */

        //DODAWANIE POKOJU
      /*  Room room = new Room();
        room.setCapacity(2);
        room.setPrice(30);
        room.setTimestamp(System.currentTimeMillis());
        room.setPicturePath("pasdsth");
        room.setHotelId(hotels.get(0).getId());

        addRoom(room);

        Room room2 = new Room();
        room2.setCapacity(5);
        room2.setPrice(40);
        room2.setTimestamp(System.currentTimeMillis());
        room2.setPicturePath("pathdsds");
        room2.setHotelId(hotels.get(0).getId());

        addRoom(room2);

        //ŁADOWANIE WSZYSTKICH USEROW
      /*  List <User> allUsers = getAllUser();
        for (User u : allUsers) {
            System.out.println(u.toString());
        } *
       */

      //ŁADOWANIE WSZYSTKICH POKOI HOTELU
      /*  List<Room> rooms = getAllHotelRooms(hotels.get(0).getId());

        for (Room r: rooms) {
            System.out.println(r.toString());
        } */

      //PRZYKLAD UZYCIA LISTY Z PRIVILEGAMI
   /*   List <Privilege> list = Privilege.getAllPrivileges();
        for (Privilege p: list) {
            System.out.println(p.toString());
            System.out.println(p.getValue());
        } */

      //  WYNIK:
      //  ORDINARY
      //  1
       // RECEPTIONIST
      //  2
      //  OWNER
      //  3
      //  ADMIN
      //  4
    }
}
