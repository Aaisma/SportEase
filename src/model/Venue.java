package model;


public class Venue {

    private String name;
    private String location;
    private String openTime;
    private String closeTime;

    public Venue(String name, String location, String openTime, String closeTime) {
        this.name = name;
        this.location = location;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }
}
