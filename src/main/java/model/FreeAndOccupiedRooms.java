package model;

import java.util.ArrayList;
import java.util.List;

public class FreeAndOccupiedRooms {
    String name;
    String city;
    List<Room> freeRooms;
    List <Room> occupiedRooms;

    public FreeAndOccupiedRooms() {
        this.name = "";
        this.city = "";
        this.freeRooms = new ArrayList<>();
        this.occupiedRooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    public List<Room> getOccupiedRooms() {
        return occupiedRooms;
    }
}
