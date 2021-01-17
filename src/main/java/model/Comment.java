package model;

import connectors.DbConnector;

public class Comment {
    private int id;

    private String text;

    private long timestamp;

    private int hotelId;

    private int userId;

    public Comment() {
        this.id = 0;
        this.text = "";
        this.timestamp = 0;
        this.hotelId = 0;
        this.userId = 0;
    }

    public Comment(int id, String text, long timestamp, int hotelId, int userId) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.hotelId = hotelId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserNameToShow() {
        User user = DbConnector.loadUserById(this.getUserId());
        return user.getFirstName() + " " + user.getLastName();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ", hotelId=" + hotelId +
                ", userId=" + userId +
                '}';
    }
}
