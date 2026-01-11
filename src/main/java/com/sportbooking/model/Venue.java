package com.sportbooking.model;

public class Venue {
    private int venueId;
    private String venueName;
    private String sportType;
    private String location;
    private double pricePerHour;
    private String availability; // "available", "unavailable"
    private String facilities;
    
    // Constructors
    public Venue() {}
    
    public Venue(int venueId, String venueName, String sportType, String location, 
                 double pricePerHour, String availability, String facilities) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.sportType = sportType;
        this.location = location;
        this.pricePerHour = pricePerHour;
        this.availability = availability;
        this.facilities = facilities;
    }
    
    // Getters and Setters
    public int getVenueId() { return venueId; }
    public void setVenueId(int venueId) { this.venueId = venueId; }
    
    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }
    
    public String getSportType() { return sportType; }
    public void setSportType(String sportType) { this.sportType = sportType; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }
    
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    
    public String getFacilities() { return facilities; }
    public void setFacilities(String facilities) { this.facilities = facilities; }
    
    @Override
    public String toString() {
        return venueName + " - " + sportType + " (" + location + ")";
    }
}