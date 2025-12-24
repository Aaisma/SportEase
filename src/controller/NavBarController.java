package controller;

import view.NavBar;
import helper.NavBar;
import view.Login;
import view.SignUp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aaisma
 */
public class NavBarController {
    private NavBar view;

    public NavBarController(NavBar view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.btnHome.addActionListener(e -> showPage("Home"));
        view.btnDiscover.addActionListener(e -> showPage("Discover"));
        view.btnAbout.addActionListener(e -> showPage("About Us"));
        view.btnFeedback.addActionListener(e -> showPage("Feedback"));
        view.btnLogin.addActionListener(e -> new Login().setVisible(true));
        view.btnSignup.addActionListener(e -> new SignUp().setVisible(true));
    }

    private void showPage(String name) {
        view.getCardLayout().show(view.getContentPanel(), name);
    }
}
