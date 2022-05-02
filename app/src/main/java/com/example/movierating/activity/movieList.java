package com.example.movierating.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.movierating.R;
import com.example.movierating.fragment.movieListFragment;
import com.example.movierating.fragment.searchFragment;
import com.example.movierating.fragment.trailerListFragment;


public class movieList extends AppCompatActivity {
    movieListFragment movieFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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