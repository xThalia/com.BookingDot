package services;

import model.ReservationInfoToShow;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailService {
    public boolean sendEmailWithRegistrationToken(String email, String token) {

       final String to = email;
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

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Complete Registration With BookingDot");
            message.setText("Dear User, provide this code when logging in to the website:\n"+token);

            System.out.println("EMAIL SERVICE - sending message");
            Transport.send(message);
            System.out.println("EMAIL SERVICE - sent message successfully");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return false;
    }

    public boolean sendEmailWithReservationConfirmation(ReservationInfoToShow info, boolean isConfirmed) {
        final String to = info.getUser().getLogin();
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

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Reservation confirmation");
            message.setText("Dear User, your reservation has been " + (isConfirmed ? "accepted" : "rejected") +"\n" +
                    "Reservation info: \n" +
                    "City: " + info.getHotel().getCity() + "\n" +
                    "Hotel name: " + info.getHotel().getName() + "\n" +
                    "Room: " + info.getRoom().getName() + "\n" +
                    "Start date: " + info.getStartDate() + "\n" +
                    "End date: " + info.getEndDate() + "\n" +
                    "Price for whole stay: " + info.getPrice() + "\n");

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

