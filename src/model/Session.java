/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aaisma
 */
public class Session {
    private static boolean isLoggedIn = false;
    private static String currentUserEmail;

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(boolean status) {
        isLoggedIn = status;
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
    }

    public static void clear() {
        isLoggedIn = false;
        currentUserEmail = null;
    }
}
