package controller;

import dao.PaymentDao;
import model.PaymentModel;
import util.ConfigLoader;
import view.PaymentForm;

import javax.swing.*;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class PaymentFormController {
    private final PaymentDao dao = new PaymentDao();
    private final PaymentForm view;

    public PaymentFormController(PaymentForm view) {
        this.view = view;
        // Load Stripe secret key from config
        Stripe.apiKey = ConfigLoader.get("STRIPE_SECRET_KEY");
    }

    /**
     * Handle cash payment flow.
     */
    public void handleCashPayment(int bookingId) {
        PaymentModel payment = new PaymentModel();
        payment.setBookingId(bookingId);
        payment.setMethod("Cash");
        payment.setStatus("Paid");
        payment.setToken(null); // no token for cash

        boolean success = dao.addPayment(payment);
        JOptionPane.showMessageDialog(view,
                success ? "Cash payment recorded!" : "Cash payment failed.");
    }

    /**
     * Handle Stripe payment flow.
     */
    public void handleStripePayment(int bookingId, double amount, String token) {
        PaymentModel payment = new PaymentModel();
        payment.setBookingId(bookingId);
        payment.setMethod("Stripe");
        payment.setToken(token);

        try {
            PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                    .setAmount((long)(amount * 100)) // smallest currency unit
                    .setCurrency("usd")              // or "npr" if supported
                    .addPaymentMethodType("card")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            payment.setStatus("succeeded".equals(intent.getStatus()) ? "Paid" : "Failed");

        } catch (StripeException e) {
            payment.setStatus("Failed");
            JOptionPane.showMessageDialog(view, "Stripe error: " + e.getMessage());
        }

        boolean success = dao.addPayment(payment);
        JOptionPane.showMessageDialog(view,
                success ? "Stripe payment recorded!" : "Stripe payment failed.");
    }
}