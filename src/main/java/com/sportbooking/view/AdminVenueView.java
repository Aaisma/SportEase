package com.sportbooking.view;

import com.sportbooking.model.Venue;
import com.sportbooking.dao.VenueDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminVenueView extends JFrame {
    private VenueDAO venueDAO;
    private JTable venueTable;
    private DefaultTableModel tableModel;
    
    private JTextField txtVenueName;
    private JTextField txtSportType;
    private JTextField txtLocation;
    private JTextField txtPrice;
    private JTextField txtFacilities;
    private JComboBox<String> cmbAvailability;
    
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnRefresh;
    
    public AdminVenueView() {
        this.venueDAO = new VenueDAO();
        
        setTitle("Venue Management");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        loadVenues();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel lblTitle = new JLabel("Venue Management", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Form Panel (Left)
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Venue Details"));
        
        formPanel.add(new JLabel("Venue Name:"));
        txtVenueName = new JTextField();
        formPanel.add(txtVenueName);
        
        formPanel.add(new JLabel("Sport Type:"));
        txtSportType = new JTextField();
        formPanel.add(txtSportType);
        
        formPanel.add(new JLabel("Location:"));
        txtLocation = new JTextField();
        formPanel.add(txtLocation);
        
        formPanel.add(new JLabel("Price/Hour:"));
        txtPrice = new JTextField();
        formPanel.add(txtPrice);
        
        formPanel.add(new JLabel("Facilities:"));
        txtFacilities = new JTextField();
        formPanel.add(txtFacilities);
        
        formPanel.add(new JLabel("Availability:"));
        cmbAvailability = new JComboBox<>(new String[]{"available", "unavailable"});
        formPanel.add(cmbAvailability);
        
        // Button Panel for form
        JPanel formButtonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        btnAdd = new JButton("Add Venue");
        btnAdd.addActionListener(e -> addVenue());
        
        btnUpdate = new JButton("Update Venue");
        btnUpdate.addActionListener(e -> updateVenue());
        
        btnDelete = new JButton("Delete Venue");
        btnDelete.addActionListener(e -> deleteVenue());
        
        btnClear = new JButton("Clear Form");
        btnClear.addActionListener(e -> clearForm());
        
        formButtonPanel.add(btnAdd);
        formButtonPanel.add(btnUpdate);
        formButtonPanel.add(btnDelete);
        formButtonPanel.add(btnClear);
        
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(formButtonPanel, BorderLayout.SOUTH);
        
        // Table Panel (Right)
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Venue List"));
        
        String[] columns = {"ID", "Venue Name", "Sport Type", "Location", "Price/Hour", "Availability", "Facilities"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        venueTable = new JTable(tableModel);
        venueTable.setRowHeight(25);
        venueTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        venueTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromTable();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(venueTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        btnRefresh = new JButton("Refresh Table");
        btnRefresh.addActionListener(e -> loadVenues());
        tablePanel.add(btnRefresh, BorderLayout.SOUTH);
        
        // Split Pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, tablePanel);
        splitPane.setDividerLocation(400);
        
        mainPanel.add(splitPane, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private void loadVenues() {
        tableModel.setRowCount(0);
        List<Venue> venues = venueDAO.getAllVenues();
        
        for (Venue venue : venues) {
            Object[] row = {
                venue.getVenueId(),
                venue.getVenueName(),
                venue.getSportType(),
                venue.getLocation(),
                "$" + venue.getPricePerHour(),
                venue.getAvailability(),
                venue.getFacilities()
            };
            tableModel.addRow(row);
        }
    }
    
    private void addVenue() {
        if (!validateForm()) return;
        
        Venue venue = new Venue();
        venue.setVenueName(txtVenueName.getText().trim());
        venue.setSportType(txtSportType.getText().trim());
        venue.setLocation(txtLocation.getText().trim());
        venue.setPricePerHour(Double.parseDouble(txtPrice.getText().trim()));
        venue.setAvailability((String) cmbAvailability.getSelectedItem());
        venue.setFacilities(txtFacilities.getText().trim());
        
        if (venueDAO.addVenue(venue)) {
            JOptionPane.showMessageDialog(this, "Venue added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadVenues();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add venue!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateVenue() {
        int selectedRow = venueTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a venue to update!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateForm()) return;
        
        int venueId = (int) tableModel.getValueAt(selectedRow, 0);
        
        Venue venue = new Venue();
        venue.setVenueId(venueId);
        venue.setVenueName(txtVenueName.getText().trim());
        venue.setSportType(txtSportType.getText().trim());
        venue.setLocation(txtLocation.getText().trim());
        venue.setPricePerHour(Double.parseDouble(txtPrice.getText().trim()));
        venue.setAvailability((String) cmbAvailability.getSelectedItem());
        venue.setFacilities(txtFacilities.getText().trim());
        
        if (venueDAO.updateVenue(venue)) {
            JOptionPane.showMessageDialog(this, "Venue updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadVenues();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update venue!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteVenue() {
        int selectedRow = venueTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a venue to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int venueId = (int) tableModel.getValueAt(selectedRow, 0);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this venue?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (venueDAO.deleteVenue(venueId)) {
                JOptionPane.showMessageDialog(this, "Venue deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadVenues();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete venue!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void fillFormFromTable() {
        int selectedRow = venueTable.getSelectedRow();
        if (selectedRow != -1) {
            txtVenueName.setText((String) tableModel.getValueAt(selectedRow, 1));
            txtSportType.setText((String) tableModel.getValueAt(selectedRow, 2));
            txtLocation.setText((String) tableModel.getValueAt(selectedRow, 3));
            String price = ((String) tableModel.getValueAt(selectedRow, 4)).replace("$", "");
            txtPrice.setText(price);
            cmbAvailability.setSelectedItem(tableModel.getValueAt(selectedRow, 5));
            txtFacilities.setText((String) tableModel.getValueAt(selectedRow, 6));
        }
    }
    
    private void clearForm() {
        txtVenueName.setText("");
        txtSportType.setText("");
        txtLocation.setText("");
        txtPrice.setText("");
        txtFacilities.setText("");
        cmbAvailability.setSelectedIndex(0);
        venueTable.clearSelection();
    }
    
    private boolean validateForm() {
        if (txtVenueName.getText().trim().isEmpty() || 
            txtSportType.getText().trim().isEmpty() ||
            txtLocation.getText().trim().isEmpty() ||
            txtPrice.getText().trim().isEmpty() ||
            txtFacilities.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}