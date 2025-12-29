package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BookingConfirmationView extends JFrame {
    private JLabel confirmationLabel;
    private JButton cashButton, esewaButton;

    public BookingConfirmationView(String bookingId) {
        setTitle("Booking Confirmation");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        confirmationLabel = new JLabel("BOOKING CONFIRMED! Your Booking ID is " + bookingId, JLabel.CENTER);
        confirmationLabel.setForeground(Color.RED);
        add(confirmationLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        cashButton = new JButton("Cash");
        esewaButton = new JButton("eSewa/khalti");
        buttonPanel.add(cashButton);
        buttonPanel.add(esewaButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void addCashListener(ActionListener listener) {
        cashButton.addActionListener(listener);
    }

    public void addEsewaListener(ActionListener listener) {
        esewaButton.addActionListener(listener);
    }
}