package controller;

import javax.swing.JOptionPane;
import model.Session;
import view.*;

/**
 *
 * @author aaisma
 */
public class AboutUsController {
    
    public void handleFeedback(){
        new Feedback().setVisible(true);
    }
    
    public void handleDiscover(){
        if(Session.isLoggedIn()){
            new Discover().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please login or register to access Discover.");
            new Register().setVisible(true);
        }
    }
}


    
