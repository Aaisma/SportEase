/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import model.OTPService;
import view.*;
/**
 *
 * @author aaisma
 */
public class OTPController {
    private final OTPVerification view;

    public OTPController(OTPVerification view) {
        this.view = view;
    }

    public void verifyOTP(String otp) {
        String email = ForgetPasswordController.getCurrentEmail();
        if (OTPService.verifyOTP(email, otp)) {
            JOptionPane.showMessageDialog(view, "OTP verified! Please reset your password.");
            view.dispose();
            new ResetPassword().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid OTP. Try again.");
        }
    }
}




