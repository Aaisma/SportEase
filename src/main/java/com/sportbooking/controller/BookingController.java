package com.sportbooking.controller;

import com.sportbooking.model.Booking;
import com.sportbooking.dao.BookingDAO;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class BookingController {
    private BookingDAO bookingDAO;
    
    public BookingController() {
        this.bookingDAO = new BookingDAO();
    }
    
    // Create new booking
    public boolean createBooking(int userId, int venueId, Date bookingDate, 
                                Time startTime, Time endTime, double totalPrice) {
        
        if (bookingDate == null || startTime == null || endTime == null) {
            JOptionPane.showMessageDialog(null, "Please fill all booking details!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Check if start time is before end time
        if (startTime.after(endTime)) {
            JOptionPane.showMessageDialog(null, "Start time must be before end time!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Booking booking = new Booking(0, userId, venueId, bookingDate, startTime, endTime, "confirmed", totalPrice);
        
        if (bookingDAO.createBooking(booking)) {
            JOptionPane.showMessageDialog(null, "Booking created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to create booking!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Get bookings by user ID
    public List<Booking> getUserBookings(int userId) {
        return bookingDAO.getBookingsByUserId(userId);
    }
    
    // Get all bookings (admin)
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
    
    // Cancel booking
    public boolean cancelBooking(int bookingId) {
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to cancel this booking?", 
            "Confirm Cancellation", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (bookingDAO.cancelBooking(bookingId)) {
                JOptionPane.showMessageDialog(null, "Booking cancelled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to cancel booking!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
    
    // Update booking status (admin)
    public boolean updateBookingStatus(int bookingId, String status) {
        if (bookingDAO.updateBookingStatus(bookingId, status)) {
            JOptionPane.showMessageDialog(null, "Booking status updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update status!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Calculate total price based on hours
    public double calculatePrice(Time startTime, Time endTime, double pricePerHour) {
        long diffInMillies = endTime.getTime() - startTime.getTime();
        double hours = diffInMillies / (1000.0 * 60 * 60);
        return hours * pricePerHour;
    }
}