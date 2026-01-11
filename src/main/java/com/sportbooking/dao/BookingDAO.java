package com.sportbooking.dao;

import com.sportbooking.model.Booking;
import com.sportbooking.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    
    // Create new booking
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, venue_id, booking_date, start_time, end_time, status, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getVenueId());
            pstmt.setDate(3, booking.getBookingDate());
            pstmt.setTime(4, booking.getStartTime());
            pstmt.setTime(5, booking.getEndTime());
            pstmt.setString(6, booking.getStatus());
            pstmt.setDouble(7, booking.getTotalPrice());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get bookings by user ID
    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.*, v.venue_name FROM bookings b " +
                     "JOIN venues v ON b.venue_id = v.venue_id " +
                     "WHERE b.user_id = ? ORDER BY b.booking_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("venue_id"),
                    rs.getDate("booking_date"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getString("status"),
                    rs.getDouble("total_price")
                );
                booking.setVenueName(rs.getString("venue_name"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    // Get all bookings (admin)
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.*, v.venue_name, u.username FROM bookings b " +
                     "JOIN venues v ON b.venue_id = v.venue_id " +
                     "JOIN users u ON b.user_id = u.user_id " +
                     "ORDER BY b.booking_date DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("venue_id"),
                    rs.getDate("booking_date"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getString("status"),
                    rs.getDouble("total_price")
                );
                booking.setVenueName(rs.getString("venue_name"));
                booking.setUsername(rs.getString("username"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    // Cancel booking
    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status = 'cancelled' WHERE booking_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update booking status
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}