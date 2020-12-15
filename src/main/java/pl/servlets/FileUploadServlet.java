package pl.servlets;

import model.Room;
import tools.UsefulFunctions;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FileUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Room room = new Room();

        //Tworzenie folderu do przesylania tam zdjec na serwerze, lub nie jesli juz istnieje
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        //

        String fileName;
        String uploadedFileName;

        //Pobieranie zawartosci pliku czy cos xD
        for (Part part : request.getParts()) {
            fileName = getFileName(part);

            //Sprawdzanie czy plik ma rozszerzenie jpg, jpeg, i inne takie
            if(UsefulFunctions.isImageFile(fileName)) {
                //Nadawanie nowej nazwy
                uploadedFileName = UUID.randomUUID().toString();
                //Zapisywanie pliku pod wlasciwa sciezke
                part.write(uploadFilePath + File.separator + uploadedFileName + ".jpg");
                //Dodawanie sciezki do zdjecia, zeby potem to poszlo na baze danych
                room.setPicturePath(uploadFilePath + File.separator + uploadedFileName + ".jpg");

                request.setAttribute("message", uploadedFileName + " File uploaded successfully!");
            } else {
                request.setAttribute("message", "That's not an image file.");
            }
        }

        //nie wiem czy to jest konieczne, to chyba jakies przekierowanie
        getServletContext().getRequestDispatcher("/response.jsp").forward(
                request, response);
    }

    //To jest funkcja do pobierania nazwy pliku
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}