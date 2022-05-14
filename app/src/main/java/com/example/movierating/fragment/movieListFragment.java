package com.example.movierating.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.movierating.R;
import com.example.movierating.adapter.movieListAdapter;
import com.example.movierating.database.DB_Movie;
import com.example.movierating.entity.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link movieListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class movieListFragment extends Fragment {
    private ListView idListView;
    private  DB_Movie db_movie;
    private List<Movie> movies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        idListView = view.findViewById(R.id.idmovielistview);
        db_movie = DB_Movie.getInMemoryDatabase(getContext());
        movies = new ArrayList<>();
        createData();

        movieListAdapter movieListAdapter = new movieListAdapter(getActivity(),R.layout.activity_item_movie_list,  movies);
        idListView.setAdapter(movieListAdapter);


        return view;
    }
    private void createData() {
        db_movie.dao_movie().insertMovie(
                new Movie("Moon Knight", "Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                        "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                        "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                        4.9,
                        2022),
                new Movie("Moon Light", "Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                        "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                        "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                        2.9,
                        2019),
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
        for (Movie m: db_movie.dao_movie().findAllMovies()
             ) {
            System.out.println(m.getMovieName());
        }
        movies =  db_movie.dao_movie().findAllMovies();
    }


}