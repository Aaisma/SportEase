/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.Connection;

public class UserDAO {
    public static boolean insertUser(String name, String email, String password, String question, String answer) {
        MySqlConnection db = new MySqlConnection();
        Connection conn = db.openConnection();

        if (conn == null) {
            return false;
        }

        String query = String.format(
            "INSERT INTO users (full_name, email, password, security_question, answer) VALUES ('%s', '%s', '%s', '%s', '%s')",
            name, email, password, question, answer
        );

        int result = db.executeUpdate(conn, query);
        db.closeConnection(conn);

        return result > 0;
    }
}

