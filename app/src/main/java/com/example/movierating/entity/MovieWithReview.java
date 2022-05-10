package com.example.movierating.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MovieWithReview {
    @Embedded public Movie movie;
    @Relation(
            parentColumn = "id",
            entityColumn = "movieID"
    )
    public List<Movie> movieList;
}
