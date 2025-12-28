/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aaisma
 */

public class OTPService {
    private static final Map<String, String> otpStore = new HashMap<>();

    public static String generateOTP(int length) {
        String digits = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }
        return otp.toString();
    }

    public static void storeOTP(String email, String otp) {
        otpStore.put(email, otp);
    }

    public static boolean verifyOTP(String email, String otp) {
        return otp.equals(otpStore.get(email));
    }
}