package controller;

import dao.SportDao;
import model.SportModel;
import view.Home;
import view.SportCard;

import java.util.List;

public class HomeController {
    private final Home view;
    private final SportDao dao;

    public HomeController(Home view) {
        this.view = view;
        this.dao = new SportDao();
    }
    
    private String generateStars(float rating) {
    int fullStars = (int) rating;
    StringBuilder stars = new StringBuilder();
    for (int i = 0; i < fullStars; i++) stars.append("★");
    while (stars.length() < 5) stars.append("☆");
    return stars.toString();
    }
    
    public void loadSports() {
        List<SportModel> sports = dao.getAllSports();
        view.clearCards();

        for (SportModel sport : sports) {
            SportCard card = new SportCard(
                sport.getName(),
                sport.getImagePath(),
                stars
            );
            view.addCard(card);
        }
    }
}