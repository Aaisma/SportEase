/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.JOptionPane;
import view.*;
import model.Session;
/**
 *
 * @author aaisma
 */
public class LogoutController {
    private final Logout view;

    public LogoutController(Logout view) {
        this.view = view;
    }

    public void confirmLogout() {
        Session.clear();
        JOptionPane.showMessageDialog(view, "You have been logged out.");
        view.dispose();
        new Login().setVisible(true);
    }

    public void cancelLogout() {
        view.dispose();
        new AboutUs().setVisible(true); // or whatever your main screen is
    }
}