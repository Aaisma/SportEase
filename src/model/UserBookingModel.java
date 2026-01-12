package model;

import java.sql.Time;

public class UserBookingModel {
    private String venueName;
    private String venueAddress;
    private Time startTime;
    private Time endTime;
    private double totalPrice;
    private String paymentMethod;
    private String paymentStatus;

    // Getters
    public String getVenueName() { return venueName; }
    public String getVenueAddress() { return venueAddress; }
    public Time getStartTime() { return startTime; }
    public Time getEndTime() { return endTime; }
    public double getTotalPrice() { return totalPrice; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }

    // Setters
    public void setVenueName(String venueName) { this.venueName = venueName; }
    public void setVenueAddress(String venueAddress) { this.venueAddress = venueAddress; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}