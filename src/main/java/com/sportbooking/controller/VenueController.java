/**
 * Controller class for managing venue operations
 * Handles venue booking, availability checks, and CRUD operations
 * @author Yubraj Joshi
 */
package com.sportbooking.controller;

import com.sportbooking.model.Venue;
import com.sportbooking.dao.VenueDAO;
import javax.swing.JOptionPane;
import java.util.List;

public class VenueController {
    private VenueDAO venueDAO;
    
    public VenueController() {
        this.venueDAO = new VenueDAO();
    }
    
    // Get all venues
    public List<Venue> getAllVenues() {
        return venueDAO.getAllVenues();
    }
    
    // Get available venues only
    public List<Venue> getAvailableVenues() {
        return venueDAO.getAvailableVenues();
    }
    
    // Add new venue (admin only)
    public boolean addVenue(String venueName, String sportType, String location, 
                           double pricePerHour, String availability, String facilities) {
        
        if (venueName.isEmpty() || sportType.isEmpty() || location.isEmpty() || facilities.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (pricePerHour <= 0) {
            JOptionPane.showMessageDialog(null, "Price must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Venue venue = new Venue(0, venueName, sportType, location, pricePerHour, availability, facilities);
        
        if (venueDAO.addVenue(venue)) {
            JOptionPane.showMessageDialog(null, "Venue added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add venue!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Update venue
    public boolean updateVenue(int venueId, String venueName, String sportType, String location,
                              double pricePerHour, String availability, String facilities) {
        
        if (venueName.isEmpty() || sportType.isEmpty() || location.isEmpty() || facilities.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (pricePerHour <= 0) {
            JOptionPane.showMessageDialog(null, "Price must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Venue venue = new Venue(venueId, venueName, sportType, location, pricePerHour, availability, facilities);
        
        if (venueDAO.updateVenue(venue)) {
            JOptionPane.showMessageDialog(null, "Venue updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update venue!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Delete venue
    public boolean deleteVenue(int venueId) {
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to delete this venue?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (venueDAO.deleteVenue(venueId)) {
                JOptionPane.showMessageDialog(null, "Venue deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete venue!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
    
    // Get venue by ID
    public Venue getVenueById(int venueId) {
        return venueDAO.getVenueById(venueId);
    }
}