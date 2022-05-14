package com.example.movierating.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movierating.R;
import com.example.movierating.database.DB_Movie;
import com.example.movierating.entity.Movie;
import com.example.movierating.fragment.movieListFragment;
import com.example.movierating.fragment.searchFragment;
import com.example.movierating.fragment.trailerListFragment;

import java.util.ArrayList;
import java.util.List;


public class movieList extends AppCompatActivity {
    movieListFragment movieFrag;
    DB_Movie db_movie;


    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<>();

        db_movie = DB_Movie.getInMemoryDatabase(getApplicationContext());
        db_movie.dao_movie().deleteAll();
        movies = db_movie.dao_movie().findAllMovies();

        movieFrag = new movieListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, movieFrag, "movieFragment").commit();


        ImageView home = findViewById(R.id.home);
        ImageView search = findViewById(R.id.search);
        ImageView trailer = findViewById(R.id.trailer);
        ImageView user = findViewById(R.id.user);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new movieListFragment(), "movieFragment").commit();
                TextView tvwMovieName = findViewById(R.id.tvwName_itemMovieList);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new searchFragment(), "searchFragment").commit();

            }
        });


        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new trailerListFragment(), "movieFragment").commit();

            }
        });


    }


}