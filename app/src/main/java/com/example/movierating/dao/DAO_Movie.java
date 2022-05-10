package com.example.movierating.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movierating.entity.Movie;

import java.util.List;

@Dao
public interface DAO_Movie{

    @Query("select * from Movie")//custom sql
    LiveData<List<Movie>> getAllMovie();

    @Insert
    void insertMovie(Movie... movies);

    @Update
    void updateMovie(Movie... movies);

    @Delete
    void deleteMovie(Movie... movies);

    @Query("delete from Movie")
    void deleteAll();

}
