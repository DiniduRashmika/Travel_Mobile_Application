package com.example.travel_android_app.Activities;


public class Hotel {

    private String  id;
    private String title;
    private String author;
    private String description;


    public Hotel(String id, String title, String author, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;

    }

    public Hotel(String title, String author, String description) {

        this.title = title;
        this.author = author;
        this.description = description;

    }

    public Hotel(String title, String author) {

        this.title = title;
        this.author = author;

    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}

