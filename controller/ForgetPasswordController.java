package controller;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import model.OTPService;
import util.EmailSender;
import view.ForgetPassword;
import view.Login;
import view.OTPVerification;

public class ForgetPasswordController {
    private final ForgetPassword view;
    private static String currentEmail;

    public ForgetPasswordController(ForgetPassword view) {
        this.view = view;
    }

    public void handleBack() {
        view.dispose();
        new Login().setVisible(true);
    }

    public void sendOTP(String email) {
        try {
            currentEmail = email;
            String otp = OTPService.generateOTP(6);
            OTPService.storeOTP(email, otp);
            EmailSender.sendOTP(email, otp);

            JOptionPane.showMessageDialog(view, "OTP sent to your email!");
            view.dispose();
            new OTPVerification().setVisible(true);
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(view, "Failed to send OTP: " + e.getMessage());
        }
    }

    public static String getCurrentEmail() {
        return currentEmail;
    }
}