package model;

public class ReservationInfoToShow {
    private User user;
    private Room room;
    private Hotel hotel;
    private Reservation reservation;
    private float price;
    private String startDate;
    private String endDate;

    public ReservationInfoToShow(User user, Room room, Hotel hotel, Reservation reservation, float price, String startDate, String endDate) {
        this.user = user;
        this.room = room;
        this.hotel = hotel;
        this.reservation = reservation;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
