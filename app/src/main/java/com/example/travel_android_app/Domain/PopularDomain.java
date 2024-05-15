package com.example.travel_android_app.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {

    private String title;
    private String author;
    private String description;
    private int books;
    private boolean borrow;
    private double score;
    private String pic;
    private boolean online;
    private int price;

    public PopularDomain(String title, String author, String description, int books, boolean borrow, double score, String pic, boolean online, int price) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.books = books;
        this.borrow = borrow;
        this.score = score;
        this.pic = pic;
        this.online = online;
        this.price = price;
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

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
