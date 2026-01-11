package com.sportbooking.controller;

import com.sportbooking.model.User;
import com.sportbooking.dao.UserDAO;
import com.sportbooking.view.UserProfileView;
import javax.swing.JOptionPane;

public class UserController {
    private UserDAO userDAO;
    
    public UserController() {
        this.userDAO = new UserDAO();
    }
    
    // Login user
    public User loginUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        User user = userDAO.loginUser(username, password);
        
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return user;
    }
    
    // Register new user
    public boolean registerUser(String username, String password, String email, String phone, String role) {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Email validation
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(null, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Password validation (minimum 6 characters)
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        User user = new User(0, username, password, email, phone, role);
        
        if (userDAO.registerUser(user)) {
            JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed! Username might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Update user profile
    public boolean updateUserProfile(User user, String username, String email, String phone) {
        if (username.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        
        if (userDAO.updateUser(user)) {
            JOptionPane.showMessageDialog(null, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update profile!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Get user by ID
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
}