package com.example.movierating.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.movierating.dao.DAO_Movie;
import com.example.movierating.database.DB_Movie;
import com.example.movierating.entity.Movie;

import java.util.List;

public class RepositoryMovie {
    private List<Movie> liveData;
    private DAO_Movie dao_movie;

/*    public RepositoryMovie(Application application) {
        dao_movie = DB_Movie.getInstance(application).dao_movie();
        liveData = (List<Movie>) dao_movie.getAllMovie();
    }*/


}
