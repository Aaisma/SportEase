/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author aaisma
 */
public class VenueManagementController {
    
    public void openSportManagement(){
        SportManagement view = new SportManagement(this);
        view.setVisible(true);
    }
    
    public void openVenueManagement(){
        VenueManagement view = new VenueManagement(this);
        view.setVisible(true);
    }
    
    public void openViewBooking(){
        ViewBooking view = new ViewBooking(this);
        view.setVisible(true);
    }
}
    
            
    
}
