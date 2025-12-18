/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DBConnection;
import java.sql.ResultSet;
/**
 *
 * @author aaisma
 */
public class SignUpController {
    public static boolean registerUser(String username, String email, String password, String question, String answer) {
        
        String checkQuery = "SELECT * FROM users WHERE username = ?";
        String insertQuery = "INSERT INTO users (username, email, password, security_question, answer) VALUES(?,?,?,?,?)";
      try (Connection conn = DBConnection.getConnection()){
            
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)){
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            
            if(rs.next()){
                return false;
            }
            
        }
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, question);
            stmt.setString(5, answer);
            
            return stmt.executeUpdate()> 0;
        }
        
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
