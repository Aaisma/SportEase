/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;

public class TesDB {
    public static void main(String[] args) {
        try {
            // Load driver (optional for newer JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create connection
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sportease",
                "root",
                "12345"
            );
            
            System.out.println("Connected successfully!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
