/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SportDao;
import model.SportModel;
import view.SportManagement;

import java.util.List;
import view.VenueManagement;
import view.ViewBooking;

/**
 *
 * @author aaisma
 */
public class SportManagementController {
    private SportManagement view;
    private SportDao dao;

    public SportManagementController(SportManagement view, SportDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void loadSports() {
        view.getTableModel().setRowCount(0);
        List<SportModel> sports = dao.getAllSports();
        for (SportModel sport : sports) {
            view.getTableModel().addRow(new Object[]{
                sport.getId(), 
                sport.getName(), 
                sport.getImagePath(), 
                sport.getRating()
            });
        }
    }

    public void addSport() {
        String name = view.getNameField().getText();
        String imagePath = view.getImagePathField().getText();
        float rating = Float.parseFloat(view.getRatingField().getText());

        dao.insertSport(new SportModel(0, name, imagePath, rating));
        loadSports();
    }

    public void updateSport() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) view.getTableModel().getValueAt(selectedRow, 0);
            String name = view.getNameField().getText();
            String imagePath = view.getImagePathField().getText();
            float rating = Float.parseFloat(view.getRatingField().getText());

            dao.updateSport(new SportModel(id, name, imagePath, rating));
            loadSports();
        }
    }

    public void deleteSport() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) view.getTableModel().getValueAt(selectedRow, 0);
            dao.deleteSport(id);
            loadSports();
        }
    }
    
    public void openSportManagement(){
        new SportManagement().setVisible(true);
    }
    
    public void openVenueManagement(){
        new VenueManagement().setVisible(true);
    }
    
    public void openViewBooking(){
        new ViewBooking().setVisible(true);
    }
}
