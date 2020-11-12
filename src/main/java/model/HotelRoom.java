package model;

public class HotelRoom {
    private int hotel_id;
    private int room_id;
    private long timestamp;

    public HotelRoom() {
        this.hotel_id = 0;
        this.room_id = 0;
        this.timestamp = System.currentTimeMillis();
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
}
