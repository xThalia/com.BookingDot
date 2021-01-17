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

    public FreeAndOccupiedRooms(String name, String city, List<Room> freeRooms, List<Room> occupiedRooms) {
        this.name = name;
        this.city = city;
        this.freeRooms = freeRooms;
        this.occupiedRooms = occupiedRooms;
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

    public void setFreeRooms(List<Room> freeRooms) {
        this.freeRooms = freeRooms;
    }

    public List<Room> getOccupiedRooms() {
        return occupiedRooms;
    }

    public void setOccupiedRooms(List<Room> occupiedRooms) {
        this.occupiedRooms = occupiedRooms;
    }
}
