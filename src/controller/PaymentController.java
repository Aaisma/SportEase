package controller;

import dao.PaymentDAO;
import model.Payment;
import view.payment; // assuming your GUI class is named 'payment'

public class PaymentController {
    private PaymentDAO dao;
    private payment view;

    public PaymentController(payment view, String bookingId) {
        this.view = view;
        this.dao = new PaymentDAO();

        view.setBookingId(bookingId);

        view.addCashListener(e -> handlePayment("Cash", bookingId));
        view.addEsewaListener(e -> handlePayment("eSewa/khalti", bookingId));
    }

    private void handlePayment(String method, String bookingId) {
        Payment payment = new Payment(bookingId, method);
        boolean success = dao.savePayment(payment);
        view.showMessage(success ? "Payment saved: " + method : "Failed to save payment.");
    }
}