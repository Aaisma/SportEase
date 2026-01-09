package model;

import java.sql.Time;

public class VenueModel {
    private int id;
    private String name;
    private String address;
    private String category;
    private String imagePath;
    private Time openTime;
    private Time closeTime;
    private double price; 
    private String groundSize;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public Time getOpenTime() { return openTime; }
    public void setOpenTime(Time openTime) { this.openTime = openTime; }

    public Time getCloseTime() { return closeTime; }
    public void setCloseTime(Time closeTime) { this.closeTime = closeTime; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getGroundSize() { return groundSize; }
    public void setGroundSize(String groundSize) { this.groundSize = groundSize; }
}