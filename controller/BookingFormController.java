package controller;

import dao.BookingDao;
import model.BookingModel;
import model.Session;
import view.BookingForm;
import view.PaymentForm;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;

public class BookingFormController {
    private final BookingDao dao = new BookingDao();
    private final BookingForm view;

    private Integer lastBookingId;
    private double lastTotalPrice;
    private String lastUsername;

    public BookingFormController(BookingForm view) {
        this.view = view;
    }

    public boolean handleConfirm() {
        try {
            // Username
            String username = view.getUserNameField().getText().trim();
            if (username.isEmpty()) {
                username = Session.getCurrentUsername(); // fallback
            }

            // Date
            java.util.Date utilDate = view.getBookingDateChooser().getDate();
            if (utilDate == null) {
                JOptionPane.showMessageDialog(view, "Please select a booking date.");
                return false;
            }

            // Time validation
            int startHour = (Integer) view.getStartSpinner().getValue();
            int endHour = (Integer) view.getEndSpinner().getValue();
            if (endHour <= startHour) {
                JOptionPane.showMessageDialog(view, "End time must be after start time.");
                return false;
            }

            // Convert to SQL types
            Date sqlDate = new Date(utilDate.getTime());
            Time start = new Time(startHour * 3600000L);
            Time end = new Time(endHour * 3600000L);

            // Calculate price dynamically
            double hours = (endHour - startHour);
            double totalPrice = hours * view.getVenuePricePerHour();

            // Build booking model
            BookingModel booking = new BookingModel();
            booking.setUsername(username);
            booking.setVenueId(view.getVenueId());
            booking.setBookDate(sqlDate);
            booking.setStartTime(start);
            booking.setEndTime(end);
            booking.setTotalPrice(totalPrice);

            // Persist booking
            Integer bookingId = dao.addBooking(booking);
            if (bookingId == null) {
                JOptionPane.showMessageDialog(view, "Failed to confirm booking.");
                return false;
            }

            // Set generated ID back into model
            booking.setId(bookingId);

            // Save last booking info
            lastBookingId = bookingId;
            lastTotalPrice = totalPrice;
            lastUsername = username;

            JOptionPane.showMessageDialog(view, "Booking confirmed! Proceed to payment.");
            view.dispose();

            // Pass booking to PaymentForm
            new PaymentForm(booking).setVisible(true);

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
            return false;
        }
    }

    public void goBack(JFrame currentFrame) {
        new view.Discover().setVisible(true);
        currentFrame.dispose();
    }

    // Getters for PaymentForm handoff
    public Integer getLastBookingId() { return lastBookingId; }
    public double getLastTotalPrice() { return lastTotalPrice; }
    public String getLastUsername() { return lastUsername; }
}