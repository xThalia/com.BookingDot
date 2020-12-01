package model;

public class HotelRoom {
    private int hotelId;
    private int roomId;
    private long timestamp;

    public HotelRoom() {
        this.hotelId = 0;
        this.roomId = 0;
        this.timestamp = System.currentTimeMillis();
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
