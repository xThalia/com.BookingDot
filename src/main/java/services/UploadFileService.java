package services;

import model.Room;

import java.io.File;
import java.util.UUID;

public class UploadFileService {
    public void uploadFile(Room room) {
        File uploads = new File(System.getProperty("catalina.base"+File.pathSeparator+"uploads"));
        String fileName = UUID.randomUUID().toString();


    }
}
