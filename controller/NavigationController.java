package controller;

import view.BookingForm;
import view.PaymentForm;
import view.ViewBooking;
import view.UserProfile;

public class NavigationController {

    public void openVenueManagement(java.awt.Component current) {
        // Replace with your VenueManagement view class
        new view.VenueManagement().setVisible(true);
        if (current != null && current instanceof javax.swing.JFrame) ((javax.swing.JFrame) current).dispose();
    }

    public void openViewBooking(java.awt.Component current) {
        new ViewBooking().setVisible(true);
        if (current != null && current instanceof javax.swing.JFrame) ((javax.swing.JFrame) current).dispose();
    }

    public void openUserProfile(String username, java.awt.Component current) {
        new UserProfile().setVisible(true);
        if (current != null && current instanceof javax.swing.JFrame) ((javax.swing.JFrame) current).dispose();
    }
}