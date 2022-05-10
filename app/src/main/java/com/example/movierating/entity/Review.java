package com.example.movierating.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"userID", "movieID"})
public class Review {
    private String comment;

    private int rating;
    private int userID;
    private int movieID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
@Ignore
    public Review(String comment, int rating, int userID, int movieID) {
        this.comment = comment;
        this.rating = rating;
        this.userID = userID;
        this.movieID = movieID;
    }


    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
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


    public Review() {
    }


}
