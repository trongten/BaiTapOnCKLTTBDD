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
import com.example.movierating.database.DatabaseHandler;
import com.example.movierating.entity.Movie;

import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link movieListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class trailerListFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_trailer_list, container, false);
        idListView = view.findViewById(R.id.idmovielistview);

        DatabaseHandler d = new DatabaseHandler(getContext());
        if (!d.getAllMoive().isEmpty()) {
            movies = d.getAllMoive();
        }

        movieListAdapter movieListAdapter = new movieListAdapter(getActivity(), R.layout.activity_item_trailer_list, movies);
        idListView.setAdapter(movieListAdapter);

        return view;
    }

}