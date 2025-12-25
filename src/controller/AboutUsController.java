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
    
    public void handleHome(){
        if(Session.isLoggedIn()){
            new Home().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please login or register to access Home.");
            new Register().setVisible(true);
        }
    }
    
    public void handleDiscover(){
        new Discover().setVisible(true);
    }
}


    
