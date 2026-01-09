package controller;

import javax.swing.JOptionPane;
import model.Session;
import dao.userDao;
import view.*;

public class LoginController {
    private final Login view;

    public LoginController(Login view){
        this.view = view;
    }
    
    public void handleBack(){
        view.dispose();
        new AboutUs().setVisible(true);
    }

    public void handleLogin(String username, String password){
        userDao dao = new userDao();

        if (dao.validateUser(username, password)) {
            String role = dao.getUserRole(username, password);

            // ✅ Store session info
            Session.setLoggedIn(true);
            Session.setCurrentUsername(username);   // now available in UserProfileController
            Session.setCurrentUserEmail(username);  // if you treat login as email, keep this too

            JOptionPane.showMessageDialog(view, "Login successful! Welcome " + role);
            view.dispose();

            if ("ADMIN".equalsIgnoreCase(role)) {
                new VenueManagement().setVisible(true);
            } else if ("USER".equalsIgnoreCase(role)) {
                new UserProfile().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Unknown role. Contact support.");
            }

        } else {
            JOptionPane.showMessageDialog(view, "Invalid username or password.");
        }
    }

    public void goToRegister(){
        view.dispose();
        new Register().setVisible(true);
    }

    public void goToForgotPassword(){
        view.dispose();
        new ForgetPassword().setVisible(true);
    }
}