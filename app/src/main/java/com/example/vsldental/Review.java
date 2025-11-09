package com.example.vsldental;

public class Review  {
    private String name, comment;
    private float rating;

    public Review(String name, String comment, float rating) {
        this.name = name;
        this.comment = comment;
        this.rating = rating;
    }

    public String getName() { return name; }
    public String getComment() { return comment; }

    public float getRating() { return rating; }
}
