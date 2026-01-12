package dao;

import database.Database;
import database.MySqlConnection;
import model.UserBookingModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserBookingDao {
    private final Database db = new MySqlConnection();

    // Refresh: find all bookings for a user
    public List<UserBookingModel> findByUser(String username) {
        List<UserBookingModel> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = """
                SELECT v.name AS venue_name,
                       v.address AS venue_address,
                       b.start_time,
                       b.end_time,
                       b.total_price,
                       p.method AS payment_method,
                       p.status AS payment_status
                FROM bookings b
                JOIN venues v ON b.venue_id = v.id
                LEFT JOIN payments p ON b.id = p.booking_id
                WHERE b.user_name = ?
                ORDER BY b.book_date DESC
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(conn);
        }
        return list;
    }

    // Search bookings for a user
    public List<UserBookingModel> search(String username, String keyword) {
        List<UserBookingModel> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = """
                SELECT v.name AS venue_name,
                       v.address AS venue_address,
                       b.start_time,
                       b.end_time,
                       b.total_price,
                       p.method AS payment_method,
                       p.status AS payment_status
                FROM bookings b
                JOIN venues v ON b.venue_id = v.id
                LEFT JOIN payments p ON b.id = p.booking_id
                WHERE b.user_name = ?
                  AND (v.name LIKE ? OR v.address LIKE ? OR p.status LIKE ?)
                ORDER BY b.book_date DESC
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            String like = "%" + keyword + "%";
            ps.setString(1, username);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(conn);
        }
        return list;
    }

    // Cancel booking
    public boolean cancelBooking(int bookingId, String username) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "DELETE FROM bookings WHERE id=? AND user_name=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingId);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }

    // Helper: map ResultSet to UserBookingModel
    private UserBookingModel map(ResultSet rs) throws Exception {
        UserBookingModel ub = new UserBookingModel();
        ub.setVenueName(rs.getString("venue_name"));
        ub.setVenueAddress(rs.getString("venue_address"));
        ub.setStartTime(rs.getTime("start_time"));
        ub.setEndTime(rs.getTime("end_time"));
        ub.setTotalPrice(rs.getDouble("total_price"));
        ub.setPaymentMethod(rs.getString("payment_method"));
        ub.setPaymentStatus(rs.getString("payment_status"));
        return ub;
    }
}