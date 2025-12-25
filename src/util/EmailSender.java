package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void sendOTP(String toEmail, String otp) throws MessagingException {
        final String fromEmail = "yourgmail@gmail.com";
        final String appPassword = "your-app-password"; // Change per branch to check. Will edit later

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, appPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("SportEase Password Reset OTP");
        message.setText("Your OTP is: " + otp);

        Transport.send(message); // throws MessagingException if it fails
    }
}