package com.sportbooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/sport_booking_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Yuvi_091@";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }
    
    public static void main(String[] args) {
        // Test connection
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("TEST PASSED!");
        }
    }
}