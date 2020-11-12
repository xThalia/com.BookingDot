package model;

public class Room {
    private int id;
    private int capacity;
    private int price;
    private long timestamp;
    private int hotel_id;

    public Room() {
        this.id = 0;
        this.capacity = 0;
        this.price = 0;
        this.timestamp = System.currentTimeMillis();
        this.hotel_id = 0;
    }

    public Room(int capacity, int price, int timestamp, int hotel_id) {
        this.capacity = capacity;
        this.price = price;
        this.timestamp = timestamp;
        this.hotel_id = hotel_id;
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

    public int getHotel_id() {
        return hotel_id;
    }

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

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }
}
