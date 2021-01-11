package model;

import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String city;
    private long timestamp;
    private List<Room> hotelRooms;

    public Hotel() {
        this.id = 0;
        this.name = "";
        this.address = "";
        this.city = "";
        this.timestamp = System.currentTimeMillis();
    }

    public Hotel(String name, String city, long timestamp) {
        this.name = name;
        this.city = city;
        this.timestamp = timestamp;
    }

    public Hotel(String name, String city, long timestamp, List<Room> hotelRooms) {
        this.name = name;
        this.city = city;
        this.timestamp = timestamp;
        this.hotelRooms = hotelRooms;
    }

    public Hotel(String name, String address, String city, long timestamp) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<Room> getHotelRooms() { return hotelRooms; }

    public void setHotelRooms(List<Room> hotelRooms) { this.hotelRooms = hotelRooms; }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
