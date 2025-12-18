package view;

import helper.EmailSender;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ForgetPassword extends JFrame {

    private JTextField txtUsername;
    private JTextField txtEmail;
    private JButton btnReset;

    // Database credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/DATABASE userdb"; // Make sure 'userdb' exists
    private final String DB_USER = "root";
    private final String DB_PASS = "Nanu@2062";

    public ForgetPassword() {
        setTitle("Forgot Password - SportEase");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        JLabel lblTitle = new JLabel("Forgot Password");
        lblTitle.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
        lblTitle.setForeground(new Color(153, 0, 0));
        lblTitle.setBounds(250, 30, 400, 50);
        mainPanel.add(lblTitle);

        JLabel lblInfo = new JLabel("Enter your username and email to reset your password");
        lblInfo.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        lblInfo.setBounds(200, 90, 500, 25);
        mainPanel.add(lblInfo);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        lblUsername.setBounds(250, 140, 100, 25);
        mainPanel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        txtUsername.setBounds(250, 165, 300, 35);
        mainPanel.add(txtUsername);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        lblEmail.setBounds(250, 220, 100, 25);
        mainPanel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        txtEmail.setBounds(250, 245, 300, 35);
        mainPanel.add(txtEmail);

        btnReset = new JButton("Reset Password");
        btnReset.setBackground(new Color(153, 0, 0));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        btnReset.setBounds(250, 310, 300, 40);
        btnReset.addActionListener(e -> resetPassword());
        mainPanel.add(btnReset);

        // Optional back to login button
        JButton btnBack = new JButton("Back to Login");
        btnBack.setBounds(250, 370, 300, 30);
        btnBack.setForeground(new Color(153, 0, 0));
        btnBack.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(e -> {
            new Login().setVisible(true);
            this.dispose();
        });
        mainPanel.add(btnBack);

        add(mainPanel);
    }

    private void resetPassword() {
        String usernameInput = txtUsername.getText().trim();
        String emailInput = txtEmail.getText().trim();

        if (usernameInput.isEmpty() || emailInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and email.");
            return;
        }

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            // Check if username + email exist
            String sql = "SELECT * FROM users WHERE username=? AND email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, usernameInput);
            pst.setString(2, emailInput);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Generate new password
                String newPassword = "Pass" + ((int) (Math.random() * 10000));

                // Update password in DB
                String updateSQL = "UPDATE users SET password=? WHERE username=?";
                PreparedStatement updatePst = con.prepareStatement(updateSQL);
                updatePst.setString(1, newPassword);
                updatePst.setString(2, usernameInput);
                updatePst.executeUpdate();

                // Send email
                String subject = "Your New Password";
                String message = "Hello " + usernameInput + ",\n\nYour new password is: " +
                        newPassword + "\nPlease change it after login.\n\nRegards,\nSportEase Team";
                EmailSender.sendEmail(emailInput, subject, message);

                JOptionPane.showMessageDialog(this, "New password sent to your email!");
            } else {
                JOptionPane.showMessageDialog(this, "Username or Email not found!");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ForgetPassword().setVisible(true));
    }
}
