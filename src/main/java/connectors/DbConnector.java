package connectors;

import enums.Privilege;
import model.*;
import providers.*;

import services.SendEmailService;
import services.SendOfferService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DbConnector {
    public static void createBasicAccounts() {
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
        boolean result = userDataProvider.addUser(user);
        createOfferInfoForUser(user.getLogin());
        return result;
    }

    public static String createRegistrationToken(User user) {
        RegistrationTokenProvider registrationTokenProvider = new RegistrationTokenProvider();
        return registrationTokenProvider.createRegistrationToken(user);
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

    public static Room getRoomById(int id) {
        RoomProvider roomProvider = new RoomProvider();
        return roomProvider.getRoomById(id);
    }

    public static Hotel getHotelById(int id) {
        HotelProvider hotelProvider = new HotelProvider();
        return hotelProvider.getHotelById(id);
    }

    public static boolean addReservation(int roomId, int userId, String startDate, String endDate, int isConfirmed, boolean isFinished) {
        ReservationProvider provider = new ReservationProvider();
        return provider.addReservation(roomId, userId, startDate, endDate, isConfirmed, isFinished);
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

    public static List<Room> getFreeRoomsByHotelId(int hotelId, String startDate, String endDate) {
        HotelProvider hotelProvider = new HotelProvider();
        ReservationProvider reservationProvider = new ReservationProvider();
        Hotel hotel = hotelProvider.getHotelById(hotelId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDateConverted = format.parse(startDate);
            Date endDateConverted = format.parse(endDate);
            Timestamp startDateTimestamp = new Timestamp(startDateConverted.getTime());
            Timestamp endDateTimestamp = new Timestamp(endDateConverted.getTime());

            List<Room> freeRooms = new ArrayList<>();
            if(hotel != null && DbConnector.getAllHotelRooms(hotel.getId()) != null) {
                for (Room room : DbConnector.getAllHotelRooms(hotel.getId()) ) {
                    if(reservationProvider.checkReservationForRoomBetweenDate(room.getId(), startDateTimestamp, endDateTimestamp))  {
                        freeRooms.add(room);
                    }
                }
            }

            if(freeRooms.size() == 0) return null;
            else return freeRooms;

            } catch (ParseException e) {
                System.out.println("Wrong date format");
                return null;
            }
    }

    public static List<Hotel> getAllHotelsWithRoomsByOwnerId(int id) {

        List<Hotel> hotels = getAllUserHotel(id);
        for (Hotel hotel : hotels) {
            List<Room> hotelRooms = getAllHotelRooms(hotel.getId());
            if(hotelRooms.size() != 0) {
                hotel.setHotelRooms(hotelRooms);
            }
        }

        if(hotels != null && hotels.size() != 0) {
            return hotels;
        } else {
            return null;
        }
    }

    public static List<Hotel> getAllHotelsWithRooms() {

        List<Hotel> hotels = getAllHotels();
        for (Hotel hotel : hotels) {
            List<Room> hotelRooms = getAllHotelRooms(hotel.getId());
            if(hotelRooms.size() != 0) {
                hotel.setHotelRooms(hotelRooms);
            }
        }

        if(hotels.size() != 0) {
            return hotels;
        } else {
            return null;
        }
    }

    public static List<Reservation> getAllCurrentAndUpcomingReservationsByOwnerId(int id) {
        ReservationProvider reservationProvider = new ReservationProvider();
        List<Hotel> hotelsWithRooms = getAllHotelsWithRoomsByOwnerId(id);
        List<Reservation> reservations = new ArrayList<>();

        if(hotelsWithRooms == null || hotelsWithRooms.size() == 0) return null;
        for (Hotel hotel : hotelsWithRooms) {
            if(hotel.getHotelRooms() != null && hotel.getHotelRooms().size() != 0)
                for (Room room : hotel.getHotelRooms()) {
                        List<Reservation> tmpReservations = reservationProvider.getCurrentAndUpcomingReservationsForRoom(room.getId());
                        if(tmpReservations != null && tmpReservations.size() != 0)
                        reservations = Stream.concat(reservations.stream(), tmpReservations.stream())
                            .collect(Collectors.toList());
                }
        }

        if(reservations.size() != 0) return reservations;
        else return null;
    }

    public static List<ReservationInfoToShow> getAllReservationsInfo(List<Reservation> reservations) {
        List <ReservationInfoToShow> reservationsInfoList = new ArrayList<>();

        for (Reservation reservation : reservations) {
            User user = loadUserById(reservation.getUserId());
            Room room = getRoomById(reservation.getRoomId());
            Hotel hotel = new Hotel();
            if(room.getHotelId() != 0) {
                hotel = getHotelById(room.getHotelId());
            }

            if(user != null && room != null && hotel.getId() != 0) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = new Date(reservation.getStartDate());
                Date endDate = new Date(reservation.getEndDate());
                long lengthOfStay = TimeUnit.DAYS.convert(reservation.getEndDate() - reservation.getStartDate(), TimeUnit.MILLISECONDS);
                String startDateString = dateFormat.format(startDate);
                String endDateString = dateFormat.format(endDate);

                ReservationInfoToShow info = new ReservationInfoToShow(user, room, hotel, reservation, room.getCapacity()*room.getPrice()*lengthOfStay,  startDateString, endDateString);
                reservationsInfoList.add(info);
            }
        }
        if (reservationsInfoList.size() == 0) return null;
        else return reservationsInfoList;
    }

    public static ReservationInfoToShow getReservationInfoByReservationId(int reservationId) {
        ReservationProvider reservationProvider = new ReservationProvider();
        Reservation reservation = reservationProvider.getReservationById(reservationId);

        List<Reservation> tmpReservationList = new ArrayList<>();
        List<ReservationInfoToShow> tmpReservationInfoList;
        tmpReservationList.add(reservation);

        tmpReservationInfoList = getAllReservationsInfo(tmpReservationList);
        if(tmpReservationInfoList == null || tmpReservationInfoList.size() == 0)
            return null;
        else
            return tmpReservationInfoList.get(0);
    }

    public static boolean updateReservationWithConfirmation(ReservationInfoToShow reservationInfo, int isAccepted) {
        ReservationProvider reservationProvider = new ReservationProvider();
        SendEmailService sendEmailService = new SendEmailService();
        int resultUpdateReservation = reservationProvider.updateReservationWithConfirmation(reservationInfo.getReservation().getId(), isAccepted);
        boolean resultSendEmail = false;
        if(resultUpdateReservation > 0) {
            resultSendEmail = sendEmailService.sendEmailWithReservationConfirmation(reservationInfo, isAccepted == 1);
        }
        return resultSendEmail;
    }

    public static boolean addComment(String text, int hotelId, int userId) {
        CommentProvider commentProvider = new CommentProvider();
        return commentProvider.addComment(text, userId, hotelId);
    }

    public static List<Comment> getAllHotelComments(int hotelId) {
        CommentProvider commentProvider = new CommentProvider();
        return commentProvider.getAllHotelComments(hotelId);
    }

    public static boolean createOfferInfoForUser(String login) {
        User user = loadUserByEmail(login);
        OfferProvider offerProvider = new OfferProvider();
        return offerProvider.addOffer(user.getId());
    }

    public static void sendOfferToUser(int userId) {
        OfferProvider offerProvider = new OfferProvider();
        SendOfferService sendOfferService = new SendOfferService();
        long diff = System.currentTimeMillis() - offerProvider.getTimestampByUserId(userId);

        if (diff > 0) {
            long diffInDays = TimeUnit.MILLISECONDS.toDays(
                    System.currentTimeMillis() - diff);
            if(diffInDays >= 1) {
                sendOfferService.sendEmailWithOffer(userId);
            }
        }
    }

    //do stanu zajetosci pokoi
    public static List<FreeAndOccupiedRooms> divideFreeAndOccupiedHotelRooms(int ownerId) {
        ReservationProvider reservationProvider = new ReservationProvider();
        List<Hotel> hotelsWithRooms = getAllHotelsWithRoomsByOwnerId(ownerId);
        List<FreeAndOccupiedRooms> freeAndOccupiedRoomsList = new ArrayList<>();

        for (Hotel hotel : hotelsWithRooms) {
            if(hotel.getHotelRooms() != null && hotel.getHotelRooms().size() != 0) {
                FreeAndOccupiedRooms freeAndOccupiedRooms = new FreeAndOccupiedRooms();
                freeAndOccupiedRooms.setName(hotel.getName());
                freeAndOccupiedRooms.setCity(hotel.getCity());

                for (Room room : hotel.getHotelRooms()) {
                    List<Reservation> tmpReservations = reservationProvider.getReservationsForRoomInCurrentDay(room.getId());
                    if (tmpReservations == null || tmpReservations.size() == 0) {
                        freeAndOccupiedRooms.getFreeRooms().add(room);
                    } else {
                        boolean add = true;
                        for (Reservation reservation: tmpReservations ) {
                            if (reservation.isReservationConfirmed() == 2) { //odrzucona
                                add = false;
                                break;
                            }
                        }
                        if (add) freeAndOccupiedRooms.getOccupiedRooms().add(room);
                        else freeAndOccupiedRooms.getFreeRooms().add(room);
                    }
                }

                freeAndOccupiedRoomsList.add(freeAndOccupiedRooms);
            }
        }

        if(freeAndOccupiedRoomsList.size() != 0) return freeAndOccupiedRoomsList;
        else return null;
    }

    public static void main(String[] args) {
        createBasicAccounts();
    }
}
