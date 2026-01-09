package model;

import java.sql.Time;

public class UserHistoryModel {
    private int bookingId;
    private String venueName;
    private String venueAddress;
    private Time startTime;
    private Time endTime;
    private double totalPrice;
    private String paymentMethod;

    // Getters
    public int getBookingId() { return bookingId; }
    public String getVenueName() { return venueName; }
    public String getVenueAddress() { return venueAddress; }
    public Time getStartTime() { return startTime; }
    public Time getEndTime() { return endTime; }
    public double getTotalPrice() { return totalPrice; }
    public String getPaymentMethod() { return paymentMethod; }

    // Setters
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public void setVenueName(String venueName) { this.venueName = venueName; }
    public void setVenueAddress(String venueAddress) { this.venueAddress = venueAddress; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}