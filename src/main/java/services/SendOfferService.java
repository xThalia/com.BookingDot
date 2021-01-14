package services;

import connectors.DbConnector;
import model.OfferContent;
import model.ReservationInfoToShow;
import model.User;

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
        offers.add(new OfferContent("Perfect hotel in city "," for", "per seat for one day! In hotel ","! Don't miss that!"));
        offers.add(new OfferContent("Don't miss extra occassion for stay in"," for only ", " per seat for one day! In hotel ","!"));
    }

    public boolean sendEmailWithOffer(int userId) {
        User user = DbConnector.loadUserById(userId);
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

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(EMAIL_TITLE);
            int random = new Random().nextInt(offers.size()) + 1;
            OfferContent content = offers.get(random);
            message.setText(content.getWhere()); //TODO

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
