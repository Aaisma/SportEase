package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookingcancel extends JFrame {
    private JTextArea cancellationReason;

    public bookingcancel() {
        setTitle("Cancel Booking");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // absolute positioning like your JSX

        // Heading
        JLabel heading = new JLabel("Cancel Booking");
        heading.setFont(new Font("Inter", Font.BOLD, 32));
        heading.setForeground(new Color(144, 11, 9));
        heading.setBounds(300, 30, 400, 40);
        add(heading);

        // Alert box
        JLabel alert = new JLabel("! Cancelling the booking may result in partial or non refund");
        alert.setFont(new Font("Inter", Font.BOLD, 18));
        alert.setOpaque(true);
        alert.setBackground(new Color(236, 191, 191));
        alert.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        alert.setBounds(60, 100, 700, 50);
        add(alert);

        // Booking details
        JTextArea bookingDetails = new JTextArea(
            "Booking ID: #BK10234\n" +
            "Venue: Sanepa Futsal\n" +
            "Date: 15 Feb 2026\n" +
            "Amount Paid: $1200"
        );
        bookingDetails.setFont(new Font("Inter", Font.BOLD, 16));
        bookingDetails.setEditable(false);
        bookingDetails.setBounds(60, 180, 400, 100);
        add(bookingDetails);

        // Cancellation reason
        JLabel reasonLabel = new JLabel("Reason for cancellation (optional):");
        reasonLabel.setFont(new Font("Inter", Font.BOLD, 18));
        reasonLabel.setBounds(60, 300, 400, 30);
        add(reasonLabel);

        cancellationReason = new JTextArea();
        cancellationReason.setBounds(60, 340, 700, 90);
        cancellationReason.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(cancellationReason);

        // Refund info
        JTextArea refundInfo = new JTextArea(
            "Refund Information:\n" +
            "Refund: $900 will be credited to your original method within 5-7 working days."
        );
        refundInfo.setFont(new Font("Inter", Font.PLAIN, 16));
        refundInfo.setEditable(false);
        refundInfo.setBackground(new Color(224, 255, 218));
        refundInfo.setBorder(BorderFactory.createLineBorder(new Color(59, 92, 50)));
        refundInfo.setBounds(60, 450, 700, 90);
        add(refundInfo);

        // Buttons
        JButton goBackBtn = new JButton("Go Back");
        goBackBtn.setBounds(60, 560, 150, 40);
        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Go Back clicked");
            }
        });
        add(goBackBtn);

        JButton confirmBtn = new JButton("Confirm Cancellation");
        confirmBtn.setBounds(610, 560, 200, 40);
        confirmBtn.setBackground(new Color(144, 11, 9));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Confirm Cancellation clicked. Reason: " + cancellationReason.getText());
            }
        });
        add(confirmBtn);

        setVisible(true);
    }

    public static void main(String[] args) {
        new bookingcancel(); // Test commit to verify GitHub contributions
    }
}