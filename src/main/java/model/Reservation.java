package model;

public class Reservation {
    private int id;

    private int roomId;

    private int userId;

    private long startDate;

    private long endDate;

    private boolean reservationConfirmed;

    public Reservation() {
        this.id = 0;
        this.roomId = 0;
        this.userId = 0;
        this.startDate = 0;
        this.endDate = 0;
        this.reservationConfirmed = false;
    }

    public Reservation(int roomId, int userId, long startDate, long endDate, boolean reservationConfirmed) {
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationConfirmed = reservationConfirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public boolean isReservationConfirmed() {
        return reservationConfirmed;
    }

    public void setReservationConfirmed(boolean reservationConfirmed) {
        this.reservationConfirmed = reservationConfirmed;
    }
}
