package services;

import connectors.DbConnector;
import model.*;
import providers.OfferProvider;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class SendOfferService {
    public static final String EMAIL_TITLE = "Individual offer for You!";

    public List<OfferContent> offers = new ArrayList<>();

    public SendOfferService() {
        offers.add(new OfferContent("Perfect hotel in city "," for ", "per seat for one night! In hotel ","! Don't miss that!"));
        offers.add(new OfferContent("Don't miss extra occassion for stay in"," for only ", " per seat for one night! In hotel ","!"));
    }

    public boolean sendEmailWithOffer(int userId) {
        User user = DbConnector.loadUserById(userId);
        List<Hotel> hotels = DbConnector.getAllHotelsWithRooms();

        int hotelRandNumber;
        int roomRandNumber;
        Hotel chosenHotel = new Hotel();
        Room chosenRoom = new Room();

        if (hotels == null) return false;

        for (int i = 0 ; i < 100 ; i++) {
           hotelRandNumber = (int)((Math.random() * hotels.size()) - 1);

           if(hotels.get(hotelRandNumber).getHotelRooms().size() != 0) {
               chosenHotel = hotels.get(hotelRandNumber);

               if(chosenHotel.getHotelRooms() != null && chosenHotel.getHotelRooms().size() != 0) {
                   roomRandNumber = (int) ((Math.random() * chosenHotel.getHotelRooms().size()) - 1);
                   chosenRoom = chosenHotel.getHotelRooms().get(roomRandNumber);
                   break;
               }
           }
        }

        if (chosenRoom.getId() == 0) return false;

        OfferProvider offerProvider = new OfferProvider();
        offerProvider.updateOfferTimestamp(userId);

        final String to = user.getLogin();
        final String from = "bookingdotproject@gmail.com";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "vzqqgwanhsuuhgrx");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(EMAIL_TITLE);
            int random = new Random().nextInt(offers.size() - 1);
            OfferContent content = offers.get(random);
            message.setText(content.getWhere() + chosenHotel.getCity() + content.getWhatPrice() + chosenRoom.getPrice() + content.getWhatHotel() + chosenHotel.getName() + content.getRest()); //TODO

            System.out.println("EMAIL SERVICE - sending message");
            Transport.send(message);
            System.out.println("EMAIL SERVICE - sent message successfully");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return false;
    }

}
