package com.example.movierating.entity;

public class Movie {
    private int idMovie;
    private String movieName, description, linkTrailer, linkImg;
    private Double rating;

    public Movie() {
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public Movie(int idMovie, String movieName, String description, String linkTrailer, String linkImg, Double rating) {
        this.idMovie = idMovie;
        this.movieName = movieName;
        this.description = description;
        this.linkTrailer = linkTrailer;
        this.linkImg = linkImg;
        this.rating = rating;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkTrailer() {
        return linkTrailer;
    }

    public void setLinkTrailer(String linkTrailer) {
        this.linkTrailer = linkTrailer;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
