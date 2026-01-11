package com.sportbooking.dao;

import com.sportbooking.model.Venue;
import com.sportbooking.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO {
    
    // Get all venues
    public List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        String sql = "SELECT * FROM venues";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                venues.add(new Venue(
                    rs.getInt("venue_id"),
                    rs.getString("venue_name"),
                    rs.getString("sport_type"),
                    rs.getString("location"),
                    rs.getDouble("price_per_hour"),
                    rs.getString("availability"),
                    rs.getString("facilities")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }
    
    // Get available venues
    public List<Venue> getAvailableVenues() {
        List<Venue> venues = new ArrayList<>();
        String sql = "SELECT * FROM venues WHERE availability = 'available'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                venues.add(new Venue(
                    rs.getInt("venue_id"),
                    rs.getString("venue_name"),
                    rs.getString("sport_type"),
                    rs.getString("location"),
                    rs.getDouble("price_per_hour"),
                    rs.getString("availability"),
                    rs.getString("facilities")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }
    
    // Add new venue (admin)
    public boolean addVenue(Venue venue) {
        String sql = "INSERT INTO venues (venue_name, sport_type, location, price_per_hour, availability, facilities) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, venue.getVenueName());
            pstmt.setString(2, venue.getSportType());
            pstmt.setString(3, venue.getLocation());
            pstmt.setDouble(4, venue.getPricePerHour());
            pstmt.setString(5, venue.getAvailability());
            pstmt.setString(6, venue.getFacilities());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update venue
    public boolean updateVenue(Venue venue) {
        String sql = "UPDATE venues SET venue_name = ?, sport_type = ?, location = ?, price_per_hour = ?, availability = ?, facilities = ? WHERE venue_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, venue.getVenueName());
            pstmt.setString(2, venue.getSportType());
            pstmt.setString(3, venue.getLocation());
            pstmt.setDouble(4, venue.getPricePerHour());
            pstmt.setString(5, venue.getAvailability());
            pstmt.setString(6, venue.getFacilities());
            pstmt.setInt(7, venue.getVenueId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete venue
    public boolean deleteVenue(int venueId) {
        String sql = "DELETE FROM venues WHERE venue_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, venueId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get venue by ID
    public Venue getVenueById(int venueId) {
        String sql = "SELECT * FROM venues WHERE venue_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, venueId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Venue(
                    rs.getInt("venue_id"),
                    rs.getString("venue_name"),
                    rs.getString("sport_type"),
                    rs.getString("location"),
                    rs.getDouble("price_per_hour"),
                    rs.getString("availability"),
                    rs.getString("facilities")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}