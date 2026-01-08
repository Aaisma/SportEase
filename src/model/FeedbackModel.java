/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aaisma
 */

public class FeedbackModel {
    private String name;
    private String phone;
    private String email;
    private String message;

    public FeedbackModel(String name, String phone, String email, String message) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.message = message;
    }

    public String getName(){ 
        return name; 
    }
    
    public String getPhone(){ 
        return phone; 
    }
    
    public String getEmail(){ 
        return email; 
    }
    
    public String getMessage(){ 
        return message; 
    }
}