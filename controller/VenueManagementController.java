package controller;

import dao.VenueDao;
import java.awt.Image;
import model.VenueModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.sql.Time;
import java.util.List;
import view.ViewBooking;
import view.ViewFeedback;

public class VenueManagementController {
    private final VenueDao venueDao;

    public VenueManagementController() {
        this.venueDao = new VenueDao(); // no-arg constructor
    }

    // Add venue
    public boolean handleAdd(JTable table, JTextField name, JTextField address, JTextField category,
                         JTextField open, JTextField close, JTextField imagePath,
                         JTextField price, JTextField groundSize) {
    VenueModel venue = new VenueModel();
    venue.setName(name.getText());
    venue.setAddress(address.getText());
    venue.setCategory(category.getText());
    venue.setGroundSize(groundSize.getText());

    // Validate price
    try {
        venue.setPrice(Double.parseDouble(price.getText()));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid price format. Please enter a number.");
        return false;
    }

    // Validate time
    Time openTime = parseTime(open.getText());
    Time closeTime = parseTime(close.getText());
    if (openTime == null || closeTime == null) {
        JOptionPane.showMessageDialog(null, "Invalid time format. Use HH:mm or HH:mm:ss.");
        return false;
    }
    venue.setOpenTime(openTime);
    venue.setCloseTime(closeTime);

    venue.setImagePath(imagePath.getText());

    boolean success = venueDao.insert(venue);
    if (success) {
        handleRefresh(table, null);
        clearFields(name, address, category, open, close, imagePath, price, groundSize, table);
    }
    return success;
}

public boolean handleUpdate(JTable table, JTextField name, JTextField address, JTextField category,
                            JTextField open, JTextField close, JTextField imagePath,
                            JTextField price, JTextField groundSize) {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a venue to update.");
        return false;
    }

    VenueModel venue = new VenueModel();
    venue.setId((int) table.getValueAt(selectedRow, 0));
    venue.setName(name.getText());
    venue.setAddress(address.getText());
    venue.setCategory(category.getText());
    venue.setGroundSize(groundSize.getText());

    // Validate price
    try {
        venue.setPrice(Double.parseDouble(price.getText()));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid price format. Please enter a number.");
        return false;
    }

    // Validate time
    Time openTime = parseTime(open.getText());
    Time closeTime = parseTime(close.getText());
    if (openTime == null || closeTime == null) {
        JOptionPane.showMessageDialog(null, "Invalid time format. Use HH:mm or HH:mm:ss.");
        return false;
    }
    venue.setOpenTime(openTime);
    venue.setCloseTime(closeTime);

    venue.setImagePath(imagePath.getText());

    boolean success = venueDao.update(venue);
    if (success) {
        handleRefresh(table, null);
        clearFields(name, address, category, open, close, imagePath, price, groundSize, table);
    }
    return success;
}


    // Delete venue
    public boolean handleDelete(JTable table, JTextField name, JTextField address, JTextField category,
                            JTextField open, JTextField close, JTextField imagePath,
                            JTextField price, JTextField groundSize) {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a venue to delete.");
        return false;
    }

    int id = (int) table.getValueAt(selectedRow, 0);
    boolean success = venueDao.delete(id);

    if (success) {
        handleRefresh(table, null);
        clearFields(name, address, category, open, close, imagePath, price, groundSize, table);
    }
    return success;
}

    // Refresh table
    public void handleRefresh(JTable table, JTextField searchField) {
        List<VenueModel> venues = venueDao.findAll();
        populateTable(table, venues);
    }

    // Search venues
    public void handleSearch(JTable table, JTextField searchField) {
        String keyword = searchField.getText();
        List<VenueModel> results = venueDao.search(keyword);
        populateTable(table, results);
    }

    // Load image into preview
    public void handleLoadImage(JTextField imagePathField, JLabel imagePreviewLabel) {
        String path = imagePathField.getText();
        if (path != null && !path.isEmpty()) {
            imagePreviewLabel.setIcon(new ImageIcon(path));
        }
    }

    // Browse image
    public void handleBrowseImage(JTextField imagePathField, JLabel imagePreviewLabel) {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();

        // Set the absolute path in the text field
        imagePathField.setText(file.getAbsolutePath());

        // Show preview in the label
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        // Optionally scale the image to fit the label
        Image scaledImage = icon.getImage().getScaledInstance(
                imagePreviewLabel.getWidth(),
                imagePreviewLabel.getHeight(),
                Image.SCALE_SMOOTH
        );
        imagePreviewLabel.setIcon(new ImageIcon(scaledImage));
    }
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
    
    // View Feedback
    public void handleFeedback(JFrame currentFrame) {
        new ViewFeedback().setVisible(true);
        currentFrame.dispose();
    }

    // Parse time safely
    public Time parseTime(String timeStr) {
    if (timeStr == null || timeStr.isBlank()) return null;

    String normalized;
    if (timeStr.matches("^\\d{1,2}$")) {
        normalized = String.format("%02d:00:00", Integer.valueOf(timeStr));
    } else if (timeStr.matches("^\\d{1,2}:\\d{2}$")) {
        normalized = timeStr + ":00";
    } else if (timeStr.matches("^\\d{1,2}:\\d{2}:\\d{2}$")) {
        normalized = timeStr;
    } else {
        System.err.println("Invalid time format: " + timeStr);
        return null;
    }

    try {
        return Time.valueOf(normalized);
    } catch (IllegalArgumentException e) {
        System.err.println("Invalid time value: " + normalized);
        return null;
    }
}

    // Helper: populate JTable
    private void populateTable(JTable table, List<VenueModel> venues) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);

    for (VenueModel v : venues) {
        // Ensure price is passed as Double to match JTable column type
        Double priceValue = v.getPrice();

        model.addRow(new Object[]{
            v.getId(),         
            v.getName(),                       // Name → String
            v.getAddress(),                    // Address → String
            v.getCategory(),                   // Category → String
            v.getOpenTime(),                   // Open → String
            v.getCloseTime(),                  // Close → String
            priceValue,                        // Price → Double ✅
            v.getGroundSize()                  // Ground size → String
        });
    }
}   
    
    private void clearFields(JTextField name, JTextField address, JTextField category,
                         JTextField open, JTextField close, JTextField imagePath,
                         JTextField price, JTextField groundSize, JTable table) {
    name.setText("");
    address.setText("");
    category.setText("");
    open.setText("");
    close.setText("");
    imagePath.setText("");
    price.setText("");
    groundSize.setText("");
    table.clearSelection();
}
}