package dao;

import database.Database;
import database.MySqlConnection;
import model.BookingModel;

import java.sql.*;

public class BookingDao {
    private final Database db = new MySqlConnection();

    // Insert booking and return generated ID
    public Integer addBooking(BookingModel booking) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = """
                INSERT INTO bookings (user_name, venue_id, book_date, start_time, end_time, total_price)
                VALUES (?, ?, ?, ?, ?, ?)
            """;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, booking.getUsername());
            ps.setInt(2, booking.getVenueId());
            ps.setDate(3, booking.getBookDate());
            ps.setTime(4, booking.getStartTime());
            ps.setTime(5, booking.getEndTime());
            ps.setDouble(6, booking.getTotalPrice());

            int affected = ps.executeUpdate();
            if (affected == 0) return null;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.closeConnection(conn);
        }
    }

    public Double getVenuePrice(int venueId) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "SELECT price_per_hour FROM venues WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, venueId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("price_per_hour");
            }
            return null; // venue not found
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.closeConnection(conn);
        }
    }
}