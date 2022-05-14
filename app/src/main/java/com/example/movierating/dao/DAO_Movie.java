package com.example.movierating.dao;


import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movierating.entity.Movie;

import java.util.List;


@Dao
public interface DAO_Movie{

    @Insert(onConflict = REPLACE)
    void insertMovie(Movie... movies);

    @Insert(onConflict = IGNORE)
    void insertOrRelpaceMovie(Movie... movies);

    @Update(onConflict = REPLACE)
    void updateMovie(Movie... movies);

    @Delete
    void deleteMovie(Movie... movies);

    @Query("delete from Movie")
    public void deleteAll();

    @Query("SELECT * FROM Movie")
    public List<Movie> findAllMovies();
}
