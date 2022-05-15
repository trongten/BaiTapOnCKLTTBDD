package com.example.movierating.entity;

public class Rate {
    private int movie;
    private String user;
    private String comment;
    private float rating;

    public Rate(int movie,float rating,  String comment,String user ) {
        this.movie = movie;
        this.user = user;
        this.comment = comment;
        this.rating = rating;
    }

    public Rate() {
    }

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "movie=" + movie +
                ", user='" + user + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
