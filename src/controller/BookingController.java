package controller;

import model.Booking;
import dao.BookingDAO;
import javax.swing.JOptionPane;
import view.BookingConfirmationView;

public class BookingController {
    private BookingDAO dao;
    private BookingConfirmationView view;

    public BookingController(String bookingId) {
        dao = new BookingDAO();
        view = new BookingConfirmationView(bookingId);

        view.addCashListener(e -> handlePayment("Cash", bookingId));
        view.addEsewaListener(e -> handlePayment("eSewa/khalti", bookingId));

        view.setVisible(true);
    }

    private void handlePayment(String method, String bookingId) {
        Booking booking = new Booking(bookingId, method);
        boolean success = dao.saveBooking(booking);
        if (success) {
            JOptionPane.showMessageDialog(view, "Payment method saved: " + method);
        } else {
            JOptionPane.showMessageDialog(view, "Failed to save booking.");
        }
    }
}