/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aaisma
 */
public class SportModel {
    private int id;
    private String name;
    private String imagePath;
    private float rating;

    public SportModel(int id, String name, String imagePath, float rating) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getImagePath() { return imagePath; }
    public float getRating() { return rating; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setRating(float rating) { this.rating = rating; }
}