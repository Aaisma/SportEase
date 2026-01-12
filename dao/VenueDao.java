package dao;

import database.Database;
import database.MySqlConnection;
import model.VenueModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenueDao {
    private final Database db = new MySqlConnection();

    // Insert a new venue
    public boolean insert(VenueModel venue) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "INSERT INTO venues (name, address, category, image_path, open_time, close_time, price, ground_size) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, venue.getName());
            ps.setString(2, venue.getAddress());
            ps.setString(3, venue.getCategory());
            ps.setString(4, venue.getImagePath());
            ps.setTime(5, venue.getOpenTime());
            ps.setTime(6, venue.getCloseTime());
            ps.setDouble(7, venue.getPrice());
            ps.setString(8, venue.getGroundSize());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }

    // Update an existing venue
    public boolean update(VenueModel venue) {
    Connection conn = null;
    try {
        conn = db.openConnection();
        String sql = "UPDATE venues SET name=?, address=?, category=?, image_path=?, open_time=?, close_time=?, price=?, ground_size=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, venue.getName());
        ps.setString(2, venue.getAddress());
        ps.setString(3, venue.getCategory());
        ps.setString(4, venue.getImagePath());

        //openTime and closeTime are java.sql.Time
        ps.setTime(5, venue.getOpenTime());
        ps.setTime(6, venue.getCloseTime());
        //price is double
        ps.setDouble(7, venue.getPrice());
        ps.setString(8, venue.getGroundSize());
        //id is int
        ps.setInt(9, venue.getId());

        int updated = ps.executeUpdate();
        return updated > 0;
    } catch (SQLException e) {
        System.err.println("Update failed: " + e.getMessage() +
                           " SQLState=" + e.getSQLState() +
                           " ErrorCode=" + e.getErrorCode());
        return false;
    } finally {
        db.closeConnection(conn);
    }
}

    // Delete a venue
    public boolean delete(int id) {
    Connection conn = null;
    try {
        conn = db.openConnection();
        String sql = "DELETE FROM venues WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        db.closeConnection(conn);
    }
}

    // Find all venues
    public List<VenueModel> findAll() {
        List<VenueModel> venues = new ArrayList<>();
        Connection conn = null;
        try {
            conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM venues ORDER BY created_at DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                venues.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(conn);
        }
        return venues;
    }

    // Get venue by ID
    public VenueModel getById(int id) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM venues WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return map(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(conn);
        }
        return null;
    }

    // Map a ResultSet row to VenueModel
    private VenueModel map(ResultSet rs) throws Exception {
        VenueModel v = new VenueModel();
        v.setId(rs.getInt("id"));
        v.setName(rs.getString("name"));
        v.setAddress(rs.getString("address"));
        v.setCategory(rs.getString("category"));
        v.setImagePath(rs.getString("image_path"));
        v.setOpenTime(rs.getTime("open_time"));
        v.setCloseTime(rs.getTime("close_time"));
        v.setPrice(rs.getDouble("price")); // now double
        v.setGroundSize(rs.getString("ground_size"));
        return v;
    }

    // Unified search: text fields + time availability
    public List<VenueModel> search(String keyword) {
        List<VenueModel> venues = new ArrayList<>();
        Connection conn = null;
        try {
            conn = db.openConnection();

            // Try to interpret keyword as an integer hour (e.g. "10" means 10:00)
            Integer hour = null;
            try {
                hour = Integer.parseInt(keyword);
            } catch (NumberFormatException ignored) {}

            String sql;
            PreparedStatement ps;

            if (hour != null) {
                // Search by time availability
                sql = "SELECT * FROM venues WHERE HOUR(open_time) <= ? AND HOUR(close_time) >= ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, hour);
                ps.setInt(2, hour);
            } else if (keyword.equalsIgnoreCase("morning")) {
                sql = "SELECT * FROM venues WHERE HOUR(open_time) <= 12 AND HOUR(close_time) >= 6";
                ps = conn.prepareStatement(sql);
            } else if (keyword.equalsIgnoreCase("afternoon")) {
                sql = "SELECT * FROM venues WHERE HOUR(open_time) <= 17 AND HOUR(close_time) >= 12";
                ps = conn.prepareStatement(sql);
            } else if (keyword.equalsIgnoreCase("evening")) {
                sql = "SELECT * FROM venues WHERE HOUR(open_time) <= 22 AND HOUR(close_time) >= 17";
                ps = conn.prepareStatement(sql);
            } else if (keyword.equalsIgnoreCase("night")) {
                sql = "SELECT * FROM venues WHERE HOUR(open_time) <= 24 AND HOUR(close_time) >= 22";
                ps = conn.prepareStatement(sql);
            } else {
                // General keyword search across multiple fields
                sql = "SELECT * FROM venues WHERE name LIKE ? OR category LIKE ? OR address LIKE ? OR ground_size LIKE ?";
                ps = conn.prepareStatement(sql);
                String like = "%" + keyword + "%";
                ps.setString(1, like);
                ps.setString(2, like);
                ps.setString(3, like);
                ps.setString(4, like);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                venues.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(conn);
        }
        return venues;
    }
}