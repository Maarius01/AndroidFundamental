package com.example.finalproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "trip_table")
public class Trip {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String destination;
    private String type;
    private String price;
    private String startDate;
    private String endDate;
    private String rating;
    private String urlImage;

    public Trip(String name, String destination, String type, String price, String startDate, String endDate, String rating, String urlImage) {
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rating = rating;
        this.urlImage = urlImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getRating() {
        return rating;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setName(String name) {
        name = name;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
