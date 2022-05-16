package com.example.movierating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movierating.R;
import com.example.movierating.entity.Movie;
import com.example.movierating.fragment.movieListFragment;
import com.example.movierating.fragment.searchFragment;
import com.example.movierating.fragment.trailerListFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class movieList extends AppCompatActivity {
    movieListFragment movieFrag;
    public static int screen = 1;//1 list movie, 2 search, 3 trailer, 4 user

    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<>();


        movieFrag = new movieListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, movieFrag, "movieFragment").commit();

        ImageView home = findViewById(R.id.home);
        ImageView search = findViewById(R.id.search);
        ImageView trailer = findViewById(R.id.trailer);
        ImageView user = findViewById(R.id.user);


        TextView tvHome = findViewById(R.id.tvwTitle_IconHome);
        TextView tvSearch = findViewById(R.id.tvwTitle_IconSearch);
        TextView tvUser = findViewById(R.id.tvwTitle_IconUser);
        TextView tvTrailer = findViewById(R.id.tvwTitle_IconTrailer);

        tvHome.setVisibility(View.VISIBLE);
        tvSearch.setVisibility(View.GONE);
        tvTrailer.setVisibility(View.GONE);
        tvUser.setVisibility(View.GONE);

        home.setScaleX(1F);
        search.setScaleX(1.15F);
        trailer.setScaleX(1.15F);
        user.setScaleX(1.15F);

        home.setScaleY(1F);
        search.setScaleY(1.15F);
        trailer.setScaleY(1.15F);
        user.setScaleY(1.15F);

        home.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fragmentContainerView, new movieListFragment(), "movieFragment").commit();

            home.setImageResource(R.drawable.home_select);
            trailer.setImageResource(R.drawable.play);
            search.setImageResource(R.drawable.search);
            user.setImageResource(R.drawable.user);


            tvHome.setVisibility(View.VISIBLE);
            tvSearch.setVisibility(View.GONE);
            tvTrailer.setVisibility(View.GONE);
            tvUser.setVisibility(View.GONE);

            home.setScaleX(1F);
            search.setScaleX(1.15F);
            trailer.setScaleX(1.15F);
            user.setScaleX(1.15F);

            home.setScaleY(1F);
            search.setScaleY(1.15F);
            trailer.setScaleY(1.15F);
            user.setScaleY(1.15F);

            screen = 1;
        });


        search.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fragmentContainerView, new searchFragment(), "searchFragment").commit();
            home.setImageResource(R.drawable.house);
            trailer.setImageResource(R.drawable.play);
            search.setImageResource(R.drawable.search_selected);
            user.setImageResource(R.drawable.user);

            tvHome.setVisibility(View.GONE);
            tvSearch.setVisibility(View.VISIBLE);
            tvTrailer.setVisibility(View.GONE);
            tvUser.setVisibility(View.GONE);

            home.setScaleX(1.25F);
            search.setScaleX(1F);
            trailer.setScaleX(1.25F);
            user.setScaleX(1.25F);

            home.setScaleY(1.25F);
            search.setScaleY(1F);
            trailer.setScaleY(1.25F);
            user.setScaleY(1.25F);

            screen = 2;
        });


        trailer.setOnClickListener(view -> {

            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fragmentContainerView, new trailerListFragment(), "movieFragment").commit();

            home.setImageResource(R.drawable.house);
            trailer.setImageResource(R.drawable.play_select);
            search.setImageResource(R.drawable.search);
            user.setImageResource(R.drawable.user);

            tvHome.setVisibility(View.GONE);
            tvSearch.setVisibility(View.GONE);
            tvTrailer.setVisibility(View.VISIBLE);
            tvUser.setVisibility(View.GONE);

            home.setScaleX(1.25F);
            search.setScaleX(1.25F);
            trailer.setScaleX(1F);
            user.setScaleX(1.25F);

            home.setScaleY(1.25F);
            search.setScaleY(1.25F);
            trailer.setScaleY(1F);
            user.setScaleY(1.25F);


            screen = 3;
        });

        user.setOnClickListener(view -> {

            home.setImageResource(R.drawable.house);
            trailer.setImageResource(R.drawable.play);
            search.setImageResource(R.drawable.search);
            user.setImageResource(R.drawable.user_select);

            tvHome.setVisibility(View.GONE);
            tvSearch.setVisibility(View.GONE);
            tvTrailer.setVisibility(View.GONE);
            tvUser.setVisibility(View.VISIBLE);

            home.setScaleX(1.25F);
            search.setScaleX(1.25F);
            trailer.setScaleX(1.25F);
            user.setScaleX(1F);

            home.setScaleY(1.25F);
            search.setScaleY(1.25F);
            trailer.setScaleY(1.25F);
            user.setScaleY(1F);

            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                startActivity(new Intent(getBaseContext(), Login.class));
            } else {
                startActivity(new Intent(getBaseContext(), Logout.class));
            }

            screen = 4;

        });


    }


}