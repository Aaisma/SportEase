package dao;

import database.Database;
import database.MySqlConnection;
import model.PaymentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDao {
    private final Database db = new MySqlConnection();

    /**
     * Insert a new payment record into the payments table.
     * @param payment PaymentModel containing bookingId, method, token, status
     * @return true if insert succeeded, false otherwise
     */
    public boolean addPayment(PaymentModel payment) {
        Connection conn = null;
        try {
            conn = db.openConnection();   // ✅ use openConnection
            String sql = "INSERT INTO payments (booking_id, method, token, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, payment.getBookingId());
            ps.setString(2, payment.getMethod());
            ps.setString(3, payment.getToken());
            ps.setString(4, payment.getStatus());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeConnection(conn);     // ✅ closeConnection
        }
    }

    /**
     * Retrieve a payment by bookingId.
     * @param bookingId the booking id
     * @return PaymentModel or null if not found
     */
    public PaymentModel findByBookingId(int bookingId) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "SELECT * FROM payments WHERE booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PaymentModel payment = new PaymentModel();
                payment.setId(rs.getInt("id"));
                payment.setBookingId(rs.getInt("booking_id"));
                payment.setMethod(rs.getString("method"));
                payment.setToken(rs.getString("token"));
                payment.setStatus(rs.getString("status"));
                return payment;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            db.closeConnection(conn);
        }
    }
}