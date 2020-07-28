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
    private int price;
    private String startDate;
    private String endDate;
    private float rating;
    private String urlImage;

    public Trip(String name, String destination, String type, int price, String startDate, String endDate, float rating, String urlImage) {
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

    public int getPrice() {
        return price;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public float getRating() {
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
