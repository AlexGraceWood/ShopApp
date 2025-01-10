package org.example;

public class Product {
    private final String name;
    private final String manufacturer;
    private final double price;
    private double rating;
    private int ratingCount;

    public Product(String name, String manufacturer, double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.rating = 0.0;
        this.ratingCount = 0;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return ratingCount == 0 ? 0.0 : rating / ratingCount;
    }

    public void addRating(int newRating) {
        this.rating += newRating;
        this.ratingCount++;
    }

    @Override
    public String toString() {
        return name + " by " + manufacturer + " - $" + price + " (Rating: " + getRating() + ")";
    }
}


