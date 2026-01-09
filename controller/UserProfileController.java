package controller;

import dao.UserBookingDao;
import model.UserBookingModel;
import model.Session;
import view.UserProfile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserProfileController {
    private final UserBookingDao dao = new UserBookingDao();
    private final UserProfile view;
    private final String username;

    public UserProfileController(UserProfile view) {
        this.view = view;
        this.username = Session.getCurrentUsername(); // ✅ pull from Session
        view.getUsernameLabel().setText("Hello, " + username);
        handleRefresh(view.getUserHistoryTable(), view.getSearchField());
    }

    // -------------------- Navigation --------------------
    public void goHome(JFrame currentFrame) {
        new view.AboutUs().setVisible(true);
        currentFrame.dispose();
    }

    public void goFeedback(JFrame currentFrame) {
        new view.Feedback().setVisible(true);
        currentFrame.dispose();
    }

    public void handleLogout(JFrame currentFrame) {
        Session.clear(); // ✅ clear session on logout
        new view.Login().setVisible(true);
        currentFrame.dispose();
    }

    // -------------------- Refresh --------------------
    public void handleRefresh(JTable table, JTextField searchField) {
        List<UserBookingModel> bookings = dao.findByUser(username);
        populateTable(table, bookings);
        searchField.setText("");
    }

    // -------------------- Search --------------------
    public void handleSearch(JTable table, JTextField searchField) {
        String keyword = searchField.getText();
        List<UserBookingModel> results = dao.search(username, keyword);
        populateTable(table, results);
    }

    // -------------------- Cancel Booking --------------------
    public boolean handleCancel(JTable table, JTextField searchField) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Select a booking to cancel.");
            return false;
        }
        int bookingId = (int) table.getValueAt(row, 0); // assumes first column is booking ID
        boolean success = dao.cancelBooking(bookingId, username);
        if (success) {
            handleRefresh(table, searchField);
            JOptionPane.showMessageDialog(view, "Booking cancelled.");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to cancel booking.");
        }
        return success;
    }

    // -------------------- Optional: Load Details --------------------
    public void handleLoadDetails(JTable table, JLabel previewLabel) {
        int row = table.getSelectedRow();
        if (row == -1) return;
        String venue = table.getValueAt(row, 1).toString();
        String status = table.getValueAt(row, 6).toString();
        previewLabel.setText("Venue: " + venue + " | Status: " + status);
    }

    // -------------------- Helper: Populate Table --------------------
    private void populateTable(JTable table, List<UserBookingModel> bookings) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (UserBookingModel b : bookings) {
            model.addRow(new Object[]{
                b.getVenueName(),
                b.getVenueAddress(),
                b.getStartTime(),
                b.getEndTime(),
                b.getTotalPrice(),
                b.getPaymentMethod(),
                b.getPaymentStatus()
            });
        }
    }
}