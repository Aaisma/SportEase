package dao;

import Database.MySqlConnection;

import java.sql.*;
import model.UserModel;

public class UserDao {

    MySqlConnection mysql = new MySqlConnection();

    // Insert new user into database
    public boolean signUp(UserModel user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        Connection conn = mysql.openConnection();
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());

            int rowsInserted = pstm.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Signup Error: " + e.getMessage());
            return false;

        } finally {
            mysql.closeConnection(conn);
        }
    }

    // Check if username or email already exists
    public boolean check(UserModel user) {
        String sql = "SELECT user_id FROM users WHERE email = ? OR username = ?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getUsername());

            try (ResultSet result = pstm.executeQuery()) {
                return result.next();   // true → duplicated user
            }

        } catch (SQLException ex) {
            System.out.println("Check Error: " + ex.getMessage());
            return false;

        } finally {
            mysql.closeConnection(conn);
        }
    }
}