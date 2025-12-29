package dao;

import model.SportModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportDao {
    private Connection conn;

    public SportDao(Connection conn) {
        this.conn = conn;
    }

    public List<SportModel> getAllSports() {
        List<SportModel> sports = new ArrayList<>();
        String sql = "SELECT id, name, image_path, rating FROM sports";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sports.add(new SportModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("image_path"),
                    rs.getFloat("rating")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sports;
    }

    public boolean insertSport(SportModel sport) {
        String sql = "INSERT INTO sports (name, image_path, rating) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sport.getName());
            stmt.setString(2, sport.getImagePath());
            stmt.setFloat(3, sport.getRating());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSport(SportModel sport) {
        String sql = "UPDATE sports SET name=?, image_path=?, rating=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sport.getName());
            stmt.setString(2, sport.getImagePath());
            stmt.setFloat(3, sport.getRating());
            stmt.setInt(4, sport.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSport(int id) {
        String sql = "DELETE FROM sports WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}