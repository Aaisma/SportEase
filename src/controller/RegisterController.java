/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.userDao;
import javax.swing.JOptionPane;
import model.userModel;
import view.*;

/**
 *
 * @author aaisma
 */

public class RegisterController {

    private final Register view;

    public RegisterController(Register view) {
        this.view = view;
    }
    
    public void handleBack(){
        view.dispose();
        new AboutUs().setVisible(true);
    }
    
    public void handleRegister(String username, String email, String password, String confirmPassword){
        if (!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(view, "Password doesn't match.");
            return;
        }
        
        userDao userdao = new userDao();
        
        if(userdao.userExists(email)){
            JOptionPane.showMessageDialog(view, "This email is already registered.");
        }
        
        userModel user = new userModel(username, email, password);
        userdao.signUp(user);
        
        JOptionPane.showMessageDialog(view, "Registration successful! Please login.");
        view.dispose();
        new Login().setVisible(true);    
    }

    public void goToLogin() {
        view.dispose();
        new Login().setVisible(true);
    }
}

