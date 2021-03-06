package model;

public class Room {
    private int id;
    private String name;
    private int capacity;
    private int price;
    private long timestamp;
    private String picturePath;
    private int hotelId;

    public Room() {
        this.id = 0;
        this.name = "";
        this.capacity = 0;
        this.price = 0;
        this.timestamp = System.currentTimeMillis();
        this.picturePath = "";
        this.hotelId = 0;
    }

    public Room(String name, int capacity, int price, int timestamp, String picturePath, int hotelId) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.timestamp = timestamp;
        this.picturePath = picturePath;
        this.hotelId = hotelId;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }

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

    public String getPicturePath() { return picturePath; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setPicturePath(String picturePath) { this.picturePath = picturePath; }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", timestamp=" + timestamp +
                ", picturePath='" + picturePath + '\'' +
                ", hotelId=" + hotelId +
                '}';
    }
}
