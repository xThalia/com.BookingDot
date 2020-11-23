package model;

public class RegistrationToken {
    private int id;
    private String value;
    private int userId;
    private long timestamp;

    public RegistrationToken() {
        this.id = 0;
        this.value = "";
        this.userId = 0;
        this.timestamp = 0;
    }

    public RegistrationToken(String value, int userId, long timestamp) {
        this.value = value;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public int getUserId() {
        return userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "RegistrationToken{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                '}';
    }
}
