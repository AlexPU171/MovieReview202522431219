package com.example.moviereview.model;

public class Review {
    private int id;
    private int movieId;
    private int userId;
    private String content;
    private float rating;
    private String date;
    private String username;

    public Review() {}

    public Review(int id, int movieId, int userId, String content,
                  float rating, String date, String username) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.content = content;
        this.rating = rating;
        this.date = date;
        this.username = username;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
