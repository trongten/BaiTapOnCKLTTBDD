package com.example.movierating.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.movierating.R;
import com.example.movierating.adapter.movieListAdapter;
import com.example.movierating.database.DB_Movie;
import com.example.movierating.entity.Movie;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link movieListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class movieListFragment extends Fragment {
    private ListView idListView;
    private DB_Movie db_movie;
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

        movieListAdapter movieListAdapter = new movieListAdapter(getActivity(), R.layout.activity_item_movie_list, movies);
        idListView.setAdapter(movieListAdapter);


        return view;
    }

    private void createData() {
        db_movie.dao_movie().deleteAll();
        db_movie.dao_movie().insertMovie(
                new Movie("Moon Knight", "Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                        "x7Krla_UxRg",
                        "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                        4.9,
                        2022),
                new Movie("Moonlight", "Moonlight is a 2016 American coming-of-age drama film written and directed by Barry Jenkins, based on Tarell Alvin McCraney's unpublished semi-autobiographical play In Moonlight Black Boys Look Blue. The film stars Trevante Rhodes, André Holland, Janelle Monáe, Ashton Sanders, Jharrel Jerome, Naomie Harris, and Mahershala Ali.",
                        "9NJj12tJzqc",
                        "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                        2.9,
                        2019),
                new Movie("La La Land", "While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.",
                        "0pdqf4P9MB8",
                        "https://th.bing.com/th/id/OIP.U9fadaqK_MGxYqO5AW0KMQHaLH?pid=ImgDet&rs=1",
                        4.9,
                        2022));
        for (Movie m : db_movie.dao_movie().findAllMovies()
        ) {
            System.out.println(m.getMovieName());
        }
        movies = db_movie.dao_movie().findAllMovies();
    }


}