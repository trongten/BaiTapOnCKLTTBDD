package com.example.movierating.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.movierating.R;
import com.example.movierating.adapter.movieListAdapter;
import com.example.movierating.entity.Movie;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link movieListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class trailerListFragment extends Fragment {

    private ListView idListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trailer_list, container, false);
        idListView = view.findViewById(R.id.idmovielistview);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Phim Hay", "tv-series", "", "", 7.0, 2021));
        movies.add(new Movie("Phim Hay", "tv-series", "", "", 4.0, 2021));
        movies.add(new Movie("Phim Hay", "tv-series", "", "", 3.0, 2021));
        movies.add(new Movie("Phim Hay", "tv-series", "", "", 4.5, 2021));
        movies.add(new Movie("Phim Hay", "tv-series", "", "", 4.0, 2021));

        movieListAdapter movieListAdapter = new movieListAdapter(getActivity(), R.layout.activity_item_trailer_list, movies);
        idListView.setAdapter(movieListAdapter);
        return view;
    }
}