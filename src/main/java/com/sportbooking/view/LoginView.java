package com.sportbooking.view;

import com.sportbooking.model.User;
import com.sportbooking.dao.UserDAO;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private UserDAO userDAO;
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    
    public LoginView() {
        userDAO = new UserDAO();
        
        setTitle("Multi-Sport Venue Booking - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel lblTitle = new JLabel("Login to Book Your Venue", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        formPanel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        formPanel.add(txtUsername);
        
        formPanel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(120, 35));
        btnLogin.addActionListener(e -> performLogin());
        
        btnRegister = new JButton("Register");
        btnRegister.setPreferredSize(new Dimension(120, 35));
        btnRegister.addActionListener(e -> openRegisterView());
        
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void performLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user = userDAO.loginUser(username, password);
        
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Open appropriate view based on role
            if (user.getRole().equals("admin")) {
                new AdminVenueView().setVisible(true);
            } else {
                // Open user dashboard - for now open profile view
                new UserProfileView(user).setVisible(true);
            }
            
            this.dispose(); // Close login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openRegisterView() {
        // Create simple registration dialog
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        JTextField regUsername = new JTextField();
        JPasswordField regPassword = new JPasswordField();
        JTextField regEmail = new JTextField();
        JTextField regPhone = new JTextField();
        String[] roles = {"user", "admin"};
        JComboBox<String> regRole = new JComboBox<>(roles);
        
        panel.add(new JLabel("Username:"));
        panel.add(regUsername);
        panel.add(new JLabel("Password:"));
        panel.add(regPassword);
        panel.add(new JLabel("Email:"));
        panel.add(regEmail);
        panel.add(new JLabel("Phone:"));
        panel.add(regPhone);
        panel.add(new JLabel("Role:"));
        panel.add(regRole);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Register New User", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            String username = regUsername.getText().trim();
            String password = new String(regPassword.getPassword());
            String email = regEmail.getText().trim();
            String phone = regPhone.getText().trim();
            String role = (String) regRole.getSelectedItem();
            
            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            User newUser = new User(0, username, password, email, phone, role);
            
            if (userDAO.registerUser(newUser)) {
                JOptionPane.showMessageDialog(this, "Registration successful! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed! Username might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}
