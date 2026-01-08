/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group03.sportease;
import database.*;
/**
 *
 * @author aaisma
 */

public class Group03SportEase {
    public static void main(String[] args) {
        Database db = new MySqlConnection();

        if (db.openConnection() != null) {
            System.out.println("Database connected successfully!");
            db.closeConnection(db.openConnection()); // optional cleanup
        } else {
            System.out.println("Failed to connect to database.");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view.AboutUs().setVisible(true);
            }
        });
    }
}
        
  
