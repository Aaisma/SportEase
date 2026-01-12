package model;

/**
 *
 * @author aaisma
 */
public class Session {
    private static boolean isLoggedIn = false;
    private static String currentUserEmail;
    private static String currentUsername;

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(boolean status) {
        isLoggedIn = status;
    }

    // -------------------- Email --------------------
    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
    }

    // -------------------- Username --------------------
    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    // -------------------- Clear session --------------------
    public static void clear() {
        isLoggedIn = false;
        currentUserEmail = null;
        currentUsername = null;
    }
}