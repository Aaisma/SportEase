package controller;

import dao.AdminBookingDao;
import model.AdminBookingModel;
import view.ViewBooking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ViewBookingController {
    private final AdminBookingDao dao;
    private final ViewBooking view;

    public ViewBookingController(ViewBooking view) {
        this.view = view;
        this.dao = new AdminBookingDao();

        // Wire buttons
        view.getLogoutButton().addActionListener(e -> handleLogout(view));
        view.getAddButton().addActionListener(e -> {
            boolean success = handleAdd();
            JOptionPane.showMessageDialog(view, success ? "Booking added!" : "Failed to add booking.");
        });
        view.getUpdateButton().addActionListener(e -> {
            boolean success = handleUpdate();
            JOptionPane.showMessageDialog(view, success ? "Booking updated!" : "Failed to update booking.");
        });
        view.getDeleteButton().addActionListener(e -> {
            boolean success = handleDelete();
            JOptionPane.showMessageDialog(view, success ? "Booking deleted!" : "Failed to delete booking.");
        });
        view.getRefreshButton().addActionListener(e -> {
            handleRefresh();
            JOptionPane.showMessageDialog(view, "Table refreshed!");
        });
        view.getSearchField().addActionListener(e -> handleSearch());

        // Wire navigation labels
        view.getVenueManagementLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleVenueManagement(view);
            }
        });
        view.getViewBookingLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleViewBooking(view);
            }
        });

        // Initial load
        handleRefresh();
    }

    // -------------------- Add booking --------------------
    public boolean handleAdd() {
        try {
            AdminBookingModel booking = new AdminBookingModel();
            booking.setUserName(view.getUsernameField().getText());
            booking.setVenueName(view.getVenueBookedField().getText());
            booking.setVenueAddress(view.getVenueBookedField().getText()); // adjust if separate field
            booking.setBookingDate(Date.valueOf(view.getBookingDateField().getText()));
            booking.setStartTime(Time.valueOf(view.getStartTimeField().getText()));
            booking.setEndTime(Time.valueOf(view.getEndTimeField().getText()));
            booking.setTotalPrice(Double.parseDouble(view.getTotalPriceField().getText()));
            booking.setPaymentStatus(view.getPaymentStatusField().getText());

            boolean success = dao.insert(booking);
            if (success) {
                handleRefresh();
                clearFields();
            }
            return success;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Invalid input: " + e.getMessage());
            return false;
        }
    }

    // -------------------- Update booking --------------------
    public boolean handleUpdate() {
        int row = view.getBookingTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Select a booking to update.");
            return false;
        }
        try {
            AdminBookingModel booking = new AdminBookingModel();
            booking.setBookingId((int) view.getBookingTable().getValueAt(row, 0));
            booking.setUserName(view.getUsernameField().getText());
            booking.setVenueName(view.getVenueBookedField().getText());
            booking.setVenueAddress(view.getVenueBookedField().getText());
            booking.setBookingDate(Date.valueOf(view.getBookingDateField().getText()));
            booking.setStartTime(Time.valueOf(view.getStartTimeField().getText()));
            booking.setEndTime(Time.valueOf(view.getEndTimeField().getText()));
            booking.setTotalPrice(Double.parseDouble(view.getTotalPriceField().getText()));
            booking.setPaymentStatus(view.getPaymentStatusField().getText());

            boolean success = dao.update(booking);
            if (success) {
                handleRefresh();
                clearFields();
            }
            return success;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Update failed: " + e.getMessage());
            return false;
        }
    }

    // -------------------- Delete booking --------------------
    public boolean handleDelete() {
        int row = view.getBookingTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Select a booking to delete.");
            return false;
        }
        int bookingId = (int) view.getBookingTable().getValueAt(row, 0);
        boolean success = dao.delete(bookingId);
        if (success) {
            handleRefresh();
            view.getBookingTable().clearSelection();
        }
        return success;
    }

    // -------------------- Refresh table --------------------
    public void handleRefresh() {
        List<AdminBookingModel> bookings = dao.findAll();
        populateTable(bookings);
    }

    // -------------------- Search bookings --------------------
    public void handleSearch() {
        String keyword = view.getSearchField().getText();
        List<AdminBookingModel> results = dao.search(keyword);
        populateTable(results);
    }

    // -------------------- Navigation --------------------
    public void handleLogout(JFrame currentFrame) {
        new view.Login().setVisible(true);
        currentFrame.dispose();
    }

    public void handleViewBooking(JFrame currentFrame) {
        new view.ViewBooking().setVisible(true);
        currentFrame.dispose();
    }

    public void handleVenueManagement(JFrame currentFrame) {
        new view.VenueManagement().setVisible(true);
        currentFrame.dispose();
    }

    // -------------------- Helpers --------------------
    private void populateTable(List<AdminBookingModel> bookings) {
        DefaultTableModel model = (DefaultTableModel) view.getBookingTable().getModel();
        model.setRowCount(0);
        for (AdminBookingModel b : bookings) {
            model.addRow(new Object[]{
                b.getBookingId(),
                b.getUserName(),
                b.getVenueName(),
                b.getVenueAddress(),
                b.getBookingDate(),
                b.getStartTime(),
                b.getEndTime(),
                b.getTotalPrice(),
                b.getPaymentMethod(),
                b.getPaymentStatus()
            });
        }
    }

    private void clearFields() {
        view.getUsernameField().setText("");
        view.getVenueBookedField().setText("");
        view.getBookingDateField().setText("");
        view.getStartTimeField().setText("");
        view.getEndTimeField().setText("");
        view.getTotalPriceField().setText("");
        view.getPaymentStatusField().setText("");
        view.getSearchField().setText("");
        view.getBookingTable().clearSelection();
    }
}