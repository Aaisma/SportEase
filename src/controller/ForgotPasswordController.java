/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author abhisek
 * 
 */
public class ForgotPasswordController {
    
    public static boolean resetPassword(String username, String question, String answer, String newPassword) {
        String checkQuery = "SELECT * FROM users WHERE username = ? AND security_question = ? AND answer = ?";
        String updateQuery = "UPDATE users SET password = ? WHERE username = ?";
        
        try (Connection conn = DBConnection.getConnection()){
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, username);
                checkStmt.setString(2, question);
                checkStmt.setString(3, answer);
                
                ResultSet rs = checkStmt.executeQuery();
                if(!rs.next()){
                    return false;
                }
            }
            
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, username);
                return updateStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
