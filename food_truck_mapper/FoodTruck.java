package dev.lab.food_truck_mapper;

public class FoodTruck {
    private int id;
    private String name;
    private String location;
    private String menu;
    private String schedule;  // This corresponds to "operation_hour"
    private double latitude;
    private double longitude;

    // Constructors, getters, and setters
    public FoodTruck() {
    }

    public FoodTruck(String name, String location, String menu, String schedule, double latitude, double longitude) {
        this.name = name;
        this.location = location;
        this.menu = menu;
        this.schedule = schedule;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
