package dao;

import database.Database;
import database.MySqlConnection;
import model.AdminBookingModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminBookingDao {
    private final Database db = new MySqlConnection();

    // Insert booking
    public boolean insert(AdminBookingModel booking) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "INSERT INTO bookings (user_name, book_date, start_time, end_time, total_price) " +
                         "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, booking.getUserName());
            ps.setDate(2, booking.getBookingDate());
            ps.setTime(3, booking.getStartTime());
            ps.setTime(4, booking.getEndTime());
            ps.setDouble(5, booking.getTotalPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }

    // Update booking
    public boolean update(AdminBookingModel booking) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "UPDATE bookings SET user_name=?, book_date=?, start_time=?, end_time=?, total_price=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, booking.getUserName());
            ps.setDate(2, booking.getBookingDate());
            ps.setTime(3, booking.getStartTime());
            ps.setTime(4, booking.getEndTime());
            ps.setDouble(5, booking.getTotalPrice());
            ps.setInt(6, booking.getBookingId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update failed: " + e.getMessage());
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }

    // Delete booking
    public boolean delete(int bookingId) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "DELETE FROM bookings WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }

    // Find all bookings with venue + payment info
    public List<AdminBookingModel> findAll() {
        List<AdminBookingModel> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = """
                SELECT b.id AS booking_id,
                       b.user_name,
                       v.name AS venue_name,
                       v.address AS venue_address,
                       b.book_date,
                       b.start_time,
                       b.end_time,
                       b.total_price,
                       p.method AS payment_method,
                       p.status AS payment_status
                FROM bookings b
                JOIN venues v ON b.venue_id = v.id
                LEFT JOIN payments p ON b.id = p.booking_id
                ORDER BY b.created_at DESC
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
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

    // Search bookings
    public List<AdminBookingModel> search(String keyword) {
        List<AdminBookingModel> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = """
                SELECT b.id AS booking_id,
                       b.user_name,
                       v.name AS venue_name,
                       v.address AS venue_address,
                       b.book_date,
                       b.start_time,
                       b.end_time,
                       b.total_price,
                       p.method AS payment_method,
                       p.status AS payment_status
                FROM bookings b
                JOIN venues v ON b.venue_id = v.id
                LEFT JOIN payments p ON b.id = p.booking_id
                WHERE b.user_name LIKE ? OR v.name LIKE ?
                ORDER BY b.book_date DESC
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
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

    // Map ResultSet to AdminBookingModel
    private AdminBookingModel map(ResultSet rs) throws Exception {
        AdminBookingModel b = new AdminBookingModel();
        b.setBookingId(rs.getInt("booking_id"));
        b.setUserName(rs.getString("user_name"));
        b.setVenueName(rs.getString("venue_name"));
        b.setVenueAddress(rs.getString("venue_address"));
        b.setBookingDate(rs.getDate("book_date"));
        b.setStartTime(rs.getTime("start_time"));
        b.setEndTime(rs.getTime("end_time"));
        b.setTotalPrice(rs.getDouble("total_price"));
        b.setPaymentMethod(rs.getString("payment_method"));
        b.setPaymentStatus(rs.getString("payment_status"));
        return b;
    }
}