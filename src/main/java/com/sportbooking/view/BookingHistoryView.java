/**
 * View class for booking history display
 * Shows user booking records and history
 * @author Yubraj Joshi
 */
package com.sportbooking.view;

import com.sportbooking.model.User;
import com.sportbooking.model.Booking;
import com.sportbooking.dao.BookingDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookingHistoryView extends JFrame {
    private User currentUser;
    private BookingDAO bookingDAO;
    private JTable bookingTable;
    private DefaultTableModel tableModel;
    private JButton btnRefresh;
    private JButton btnCancel;
    private JButton btnBack;
    
    public BookingHistoryView(User user) {
        this.currentUser = user;
        this.bookingDAO = new BookingDAO();
        
        setTitle("My Booking History");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        loadBookings();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel lblTitle = new JLabel("My Bookings", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Booking ID", "Venue", "Date", "Start Time", "End Time", "Status", "Price"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        bookingTable = new JTable(tableModel);
        bookingTable.setRowHeight(25);
        bookingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(bookingTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setPreferredSize(new Dimension(120, 35));
        btnRefresh.addActionListener(e -> loadBookings());
        
        btnCancel = new JButton("Cancel Booking");
        btnCancel.setPreferredSize(new Dimension(150, 35));
        btnCancel.addActionListener(e -> cancelBooking());
        
        btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(120, 35));
        btnBack.addActionListener(e -> dispose());
        
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnBack);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadBookings() {
        tableModel.setRowCount(0);
        List<Booking> bookings = bookingDAO.getBookingsByUserId(currentUser.getUserId());
        
        for (Booking booking : bookings) {
            Object[] row = {
                booking.getBookingId(),
                booking.getVenueName(),
                booking.getBookingDate(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus(),
                "$" + booking.getTotalPrice()
            };
            tableModel.addRow(row);
        }
    }
    
    private void cancelBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to cancel!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int bookingId = (int) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 5);
        
        if (status.equals("cancelled")) {
            JOptionPane.showMessageDialog(this, "This booking is already cancelled!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to cancel this booking?", 
            "Confirm Cancellation", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (bookingDAO.cancelBooking(bookingId)) {
                JOptionPane.showMessageDialog(this, "Booking cancelled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadBookings();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to cancel booking!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}