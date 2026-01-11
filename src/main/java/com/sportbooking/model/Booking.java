package com.sportbooking.model;

import java.sql.Date;
import java.sql.Time;

public class Booking {
    private int bookingId;
    private int userId;
    private int venueId;
    private Date bookingDate;
    private Time startTime;
    private Time endTime;
    private String status; // "confirmed", "cancelled", "pending"
    private double totalPrice;
    
    // Additional fields for display
    private String venueName;
    private String username;
    
    // Constructors
    public Booking() {}
    
    public Booking(int bookingId, int userId, int venueId, Date bookingDate, 
                   Time startTime, Time endTime, String status, double totalPrice) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.venueId = venueId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalPrice = totalPrice;
    }
    
    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public int getVenueId() { return venueId; }
    public void setVenueId(int venueId) { this.venueId = venueId; }
    
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    
    public Time getStartTime() { return startTime; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }
    
    public Time getEndTime() { return endTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    
    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}