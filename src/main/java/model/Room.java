package model;

public class Room {
    private int id;
    private int capacity;
    private int price;
    private long timestamp;
    private String picture;
    private int hotelId;

    public Room() {
        this.id = 0;
        this.capacity = 0;
        this.price = 0;
        this.timestamp = System.currentTimeMillis();
        this.picture = "";
        this.hotelId = 0;
    }

    public Room(int capacity, int price, int timestamp, String picture, int hotelId) {
        this.capacity = capacity;
        this.price = price;
        this.timestamp = timestamp;
        this.picture = picture;
        this.hotelId = hotelId;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getHotelId() {
        return hotelId;
    }

    public String getPicture() { return picture; }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setPicture(String picture) { this.picture = picture; }
}
