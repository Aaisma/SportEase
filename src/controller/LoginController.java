/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import model.Session;
import dao.userDao;
import view.*;

/**
 *
 * @author aaisma
 */
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
            Session.setLoggedIn(true);

            JOptionPane.showMessageDialog(view, "Login successful! Welcome " + role);
            view.dispose();

            if ("ADMIN".equalsIgnoreCase(role)) {
                new SportManagement().setVisible(true);
            } else if ("USER".equalsIgnoreCase(role)) {
                new UserDashboard().setVisible(true);
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
