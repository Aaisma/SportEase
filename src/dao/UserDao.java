/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import database.MySqlConnection;
import model.userModel;
import util.PasswordUtils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaisma
 */
public class userDao {
    MySqlConnection mysql = new MySqlConnection();

    // Register new USER only
    public void signUp(userModel user) {
        String sql = "INSERT INTO users(username, email, password, role) VALUES (?, ?, ?, 'USER')";
        try (Connection conn = mysql.openConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            String hashedPassword = PasswordUtils.hashPassword(user.getPassword());

            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getEmail());
            psmt.setString(3, hashedPassword);
            psmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(userDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Check if email already exists
    public boolean userExists(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setString(1, email);
            ResultSet rs = psmt.executeQuery();
            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(userDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Validate login credentials
    public boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            String hashedPassword = PasswordUtils.hashPassword(password);

            psmt.setString(1, username);
            psmt.setString(2, hashedPassword);
            ResultSet rs = psmt.executeQuery();
            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(userDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Get role after login
    public String getUserRole(String username, String password) {
        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            String hashedPassword = PasswordUtils.hashPassword(password);

            psmt.setString(1, username);
            psmt.setString(2, hashedPassword);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (SQLException ex) {
            Logger.getLogger(userDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Update password securely
    public void updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            String hashedPassword = PasswordUtils.hashPassword(newPassword);

            psmt.setString(1, hashedPassword);
            psmt.setString(2, email);
            psmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(userDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}