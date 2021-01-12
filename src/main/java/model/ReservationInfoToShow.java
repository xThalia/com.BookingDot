package model;

public class ReservationInfoToShow {
    private String firstName;
    private String lastName;
    private String email;
    private String hotelName;
    private String roomName;
    private int roomCapacity;
    private String startDate;
    private String endDate;

    public ReservationInfoToShow(String firstName, String lastName, String email, String hotelName, String roomName, int roomCapacity, String startDate, String endDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.roomCapacity = roomCapacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
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
