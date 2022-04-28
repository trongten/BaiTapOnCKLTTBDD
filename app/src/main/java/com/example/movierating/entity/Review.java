package com.example.movierating.entity;

public class Review {
    private int idReview;
    private String comment;
    private int rating;
    private User user;
    private Movie movie;

    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Review() {
    }

    public Review(int idReview, String comment, int rating, User user, Movie movie) {
        this.idReview = idReview;
        this.comment = comment;
        this.rating = rating;
        this.user = user;
        this.movie = movie;
    }

}
