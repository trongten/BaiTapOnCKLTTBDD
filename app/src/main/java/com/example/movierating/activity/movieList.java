package com.example.movierating.activity;

import android.content.Intent;
import android.net.Uri;
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
import com.facebook.drawee.view.SimpleDraweeView;

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
        createData();
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
                TextView tvwMovieName = findViewById(R.id.tvwMovieName);
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

    private void createData() {
        db_movie.dao_movie().insertMovie(
                new Movie("Moon Knight", "Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                        "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                        "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                        4.9,
                        2022),
                new Movie("Moon Knight", "Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                        "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                        "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                        4.9,
                        2022),
                new Movie("Moon Knight", "Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                        "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                        "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                        4.9,
                        2022),
                new Movie("Moon Knight", "Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                        "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                        "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                        4.9,
                        2022));

    }


}