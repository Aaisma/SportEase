package model;

public class Payment {
    private String bookingId;
    private String method;

    public Payment(String bookingId, String method) {
        this.bookingId = bookingId;
        this.method = method;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getMethod() {
        return method;
    }
}