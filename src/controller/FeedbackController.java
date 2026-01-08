/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.FeedbackDao;
import model.FeedbackModel;
import view.*;
import javax.swing.JOptionPane;
import model.Session;

/**
 *
 * @author aaisma
 */
public class FeedbackController {
    private final Feedback view;

    public FeedbackController(Feedback view) {
        this.view = view;
    }

    public void handleSubmit(String name, String phone, String email, String message) {
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Message cannot be empty.");
            return;
        }

        FeedbackModel feedback = new FeedbackModel(name, phone, email, message);
        FeedbackDao dao = new FeedbackDao();
        dao.submitFeedback(feedback);

        JOptionPane.showMessageDialog(view, "Thank you for your feedback!");
        view.clearForm(); 
    }
    
    public void handleHome(){
        if(Session.isLoggedIn()){
            new Home().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please login or register to access Home.");
            new Register().setVisible(true);
        }
    }
    
    public void handleAboutUs(){
        view.dispose();
        if(Session.isLoggedIn()){
            new UserAboutUs().setVisible(true);
        } else {
            new AboutUs().setVisible(true);
        }
    }
    
    public void handleDiscover(){
            new Discover().setVisible(true);
    }
}