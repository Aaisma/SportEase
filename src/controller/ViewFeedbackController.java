package controller;

import dao.FeedbackDao;
import model.FeedbackModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import view.VenueManagement;
import view.ViewBooking;

public class ViewFeedbackController {
    private final FeedbackDao feedbackDao;

    public ViewFeedbackController() {
        this.feedbackDao = new FeedbackDao(); // no-arg constructor
    }

    // Add feedback
    public boolean handleAdd(JTable table, JTextField name, JTextField phone,
                             JTextField email, JTextArea message) {
        String msg = message.getText().trim();
        if (msg.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Message cannot be empty.");
            return false;
        }

        FeedbackModel feedback = new FeedbackModel(
                name.getText(),
                phone.getText(),
                email.getText(),
                msg
        );

        feedbackDao.submitFeedback(feedback);
        handleRefresh(table, null);
        clearFields(name, phone, email, message, table);
        return true;
    }

    // Update feedback
    public boolean handleUpdate(JTable table, JTextField name, JTextField phone,
                                JTextField email, JTextArea message) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a feedback to update.");
            return false;
        }

        int id = (int) table.getValueAt(selectedRow, 0); // ID column
        FeedbackModel feedback = new FeedbackModel(
                id,
                name.getText(),
                phone.getText(),
                email.getText(),
                message.getText(),
                (String) table.getValueAt(selectedRow, 5) // created_at column
        );

        feedbackDao.updateFeedback(feedback);
        handleRefresh(table, null);
        clearFields(name, phone, email, message, table);
        return true;
    }

    // Delete feedback
    public boolean handleDelete(JTable table, JTextField name, JTextField phone,
                                JTextField email, JTextArea message) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a feedback to delete.");
            return false;
        }

        int id = (int) table.getValueAt(selectedRow, 0);
        feedbackDao.deleteFeedback(id);
        handleRefresh(table, null);
        clearFields(name, phone, email, message, table);
        return true;
    }

    // Refresh table
    public void handleRefresh(JTable table, JTextField searchField) {
        List<FeedbackModel> feedbacks = feedbackDao.getAllFeedback();
        populateTable(table, feedbacks);
    }

    // Search feedback
    public void handleSearch(JTable table, JTextField searchField) {
        String keyword = searchField.getText();
        List<FeedbackModel> results = feedbackDao.searchFeedback(keyword);
        populateTable(table, results);
    }

    // Logout
    public void handleLogout(JFrame currentFrame) {
        new view.Login().setVisible(true);
        currentFrame.dispose();
    }

    // View bookings
    public void handleViewBooking(JFrame currentFrame) {
        new ViewBooking().setVisible(true);
        currentFrame.dispose();
    }

    // View feedback (reload)
    public void handleVenueManagement(JFrame currentFrame) {
        new VenueManagement().setVisible(true);
        currentFrame.dispose();
    }

    // Helper: populate JTable
    private void populateTable(JTable table, List<FeedbackModel> feedbacks) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (FeedbackModel f : feedbacks) {
            model.addRow(new Object[]{
                    f.getId(),
                    f.getName(),
                    f.getPhone(),
                    f.getEmail(),
                    f.getMessage(),
                    f.getCreatedAt()
            });
        }
    }

    // Helper: clear fields
    private void clearFields(JTextField name, JTextField phone, JTextField email,
                             JTextArea message, JTable table) {
        name.setText("");
        phone.setText("");
        email.setText("");
        message.setText("");
        table.clearSelection();
    }
}