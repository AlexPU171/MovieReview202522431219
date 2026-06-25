package com.example.moviereview.model;

public class Movie {
    private int id;
    private String title;
    private String year;
    private String director;
    private float rating;
    private String poster;
    private String description;

    public Movie() {}

    public Movie(int id, String title, String year, String director,
                 float rating, String poster, String description) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.poster = poster;
        this.description = description;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
