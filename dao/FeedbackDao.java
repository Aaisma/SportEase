package dao;

import database.MySqlConnection;
import model.FeedbackModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaisma
 */
public class FeedbackDao {
    private final MySqlConnection mysql = new MySqlConnection();

    // User-side: submit feedback
    public void submitFeedback(FeedbackModel feedback) {
        String sql = "INSERT INTO feedback(name, phone, email, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = mysql.openConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            String name = (feedback.getName() == null || feedback.getName().trim().isEmpty())
                    ? "Anonymous User"
                    : feedback.getName();

            psmt.setString(1, name);
            psmt.setString(2, feedback.getPhone());
            psmt.setString(3, feedback.getEmail());
            psmt.setString(4, feedback.getMessage());

            psmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ✅ Admin: get all feedback
    public List<FeedbackModel> getAllFeedback() {
        List<FeedbackModel> list = new ArrayList<>();
        String sql = "SELECT id, name, phone, email, message, created_at FROM feedback";
        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                FeedbackModel f = new FeedbackModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("message"),
                        rs.getString("created_at")
                );
                list.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // ✅ Admin: update feedback
    public void updateFeedback(FeedbackModel feedback) {
        String sql = "UPDATE feedback SET name=?, phone=?, email=?, message=? WHERE id=?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, feedback.getName());
            ps.setString(2, feedback.getPhone());
            ps.setString(3, feedback.getEmail());
            ps.setString(4, feedback.getMessage());
            ps.setInt(5, feedback.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ✅ Admin: delete feedback
    public void deleteFeedback(int id) {
        String sql = "DELETE FROM feedback WHERE id=?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ✅ Admin: search feedback by keyword
    public List<FeedbackModel> searchFeedback(String keyword) {
        List<FeedbackModel> list = new ArrayList<>();
        String sql = "SELECT id, name, phone, email, message, created_at FROM feedback " +
                     "WHERE name LIKE ? OR email LIKE ? OR message LIKE ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FeedbackModel f = new FeedbackModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("message"),
                        rs.getString("created_at")
                );
                list.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}