/**
 * View class for user profile display
 * Shows and manages user profile information
 * @author Yubraj Joshi
 */
package com.sportbooking.view;

import com.sportbooking.model.User;
import com.sportbooking.dao.UserDAO;
import javax.swing.*;
import java.awt.*;

public class UserProfileView extends JFrame {
    private User currentUser;
    private UserDAO userDAO;
    
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JLabel lblRole;
    private JButton btnUpdate;
    private JButton btnBack;
    
    public UserProfileView(User user) {
        this.currentUser = user;
        this.userDAO = new UserDAO();
        
        setTitle("User Profile");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        loadUserData();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel lblTitle = new JLabel("My Profile", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        formPanel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        formPanel.add(txtUsername);
        
        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        
        formPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);
        
        formPanel.add(new JLabel("Role:"));
        lblRole = new JLabel();
        lblRole.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(lblRole);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnUpdate = new JButton("Update Profile");
        btnUpdate.setPreferredSize(new Dimension(150, 35));
        btnUpdate.addActionListener(e -> updateProfile());
        
        btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(150, 35));
        btnBack.addActionListener(e -> dispose());
        
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnBack);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadUserData() {
        txtUsername.setText(currentUser.getUsername());
        txtEmail.setText(currentUser.getEmail());
        txtPhone.setText(currentUser.getPhoneNumber());
        lblRole.setText(currentUser.getRole().toUpperCase());
    }
    
    private void updateProfile() {
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        
        if (username.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        currentUser.setUsername(username);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phone);
        
        if (userDAO.updateUser(currentUser)) {
            JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update profile!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}