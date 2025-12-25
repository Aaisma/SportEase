/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.FeedbackModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaisma
 */
public class FeedbackDao {
    MySqlConnection mysql = new MySqlConnection();

    public void submitFeedback(FeedbackModel feedback) {
        String sql = "INSERT INTO feedback(name, phone, email, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = mysql.openConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            String name = (feedback.getName() == null || feedback.getName().trim().isEmpty())? "Anonymous User" : feedback.getName();

            psmt.setString(1, name);
            psmt.setString(2, feedback.getPhone());
            psmt.setString(3, feedback.getEmail());
            psmt.setString(4, feedback.getMessage());

            psmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


