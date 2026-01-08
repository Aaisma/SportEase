/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.*;

/**
 *
 * @author aaisma
 */
public class NavigationController {
    
    public void goToHome(javax.swing.JFrame currentView){
        currentView.dispose();
        new Home().setVisible(true);
    }
    
    public void goToDiscover(javax.swing.JFrame currentView){
        currentView.dispose();
        new Discover().setVisible(true);
    }
    
    public void goToFeedback(javax.swing.JFrame currentView){
        currentView.dispose();
        new Feedback().setVisible(true);
    }
}
