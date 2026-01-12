package controller;

import dao.VenueDao;
import model.VenueModel;
import view.VenueCard;
import view.Feedback;
import view.UserProfile;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import view.AboutUs;

public class DiscoverController {
    private final VenueDao venueDao;

    public DiscoverController(Connection conn) {
        this.venueDao = new VenueDao();
    }

    // Load all venues into the Discover container
    public void loadAll(JPanel container) {
        container.removeAll();
        List<VenueModel> venues = venueDao.findAll();
        System.out.println("Loaded " + venues.size() + " venues from DB");
        for (VenueModel v : venues) {
            VenueCard card = new VenueCard(v);
            container.add(card);
            System.out.println("Added VenueCard for: " + v.getName());
        }
        container.revalidate();
        container.repaint();
    }

    // Search venues by keyword
    public void search(JPanel container, String keyword) {
        container.removeAll();
        List<VenueModel> venues = venueDao.search(keyword);
        if (venues.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No results found for: " + keyword);
        }
        for (VenueModel v : venues) {
            VenueCard card = new VenueCard(v);
            container.add(card);
            System.out.println("Search result: " + v.getName());
        }
        container.revalidate();
        container.repaint();
    }

    // Navigate to Home page
    public void goHome(JFrame currentFrame) {
        currentFrame.dispose();
        new AboutUs().setVisible(true);
    }

    // Navigate to Feedback page
    public void goFeedback(JFrame currentFrame) {
        currentFrame.dispose();
        new Feedback().setVisible(true);
    }

    // Navigate to User Profile page
    public void goUserProfile(JFrame currentFrame) {
        currentFrame.dispose();
        new UserProfile().setVisible(true);
    }

    // Handle logout
    public void handleLogout(JFrame currentFrame) {
        int choice = JOptionPane.showConfirmDialog(
            currentFrame,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            currentFrame.dispose();
            new AboutUs().setVisible(true); // or redirect to Login if you prefer
        }
    }
}