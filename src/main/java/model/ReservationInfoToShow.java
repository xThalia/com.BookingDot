package model;

public class ReservationInfoToShow {
    private String firstName;
    private String lastName;
    private String email;
    private String hotelName;
    private String city;
    private String roomName;
    private int roomCapacity;
    private float priceForStay;
    private String startDate;
    private String endDate;
    private boolean isConfirmed;

    public ReservationInfoToShow(String firstName, String lastName, String email, String hotelName, String city, String roomName, int roomCapacity, float priceForStay, String startDate, String endDate, boolean isConfirmed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hotelName = hotelName;
        this.city = city;
        this.roomName = roomName;
        this.roomCapacity = roomCapacity;
        this.priceForStay = priceForStay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isConfirmed = isConfirmed;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public float getPriceForStay() {
        return priceForStay;
    }

    public void setPriceForStay(float priceForStay) {
        this.priceForStay = priceForStay;
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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
