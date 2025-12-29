package dao;

import model.Payment;
import java.sql.*;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sportease.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS payments (booking_id TEXT PRIMARY KEY, method TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean savePayment(Payment payment) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO payments (booking_id, method) VALUES (?, ?)");
            ps.setString(1, payment.getBookingId());
            ps.setString(2, payment.getMethod());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}