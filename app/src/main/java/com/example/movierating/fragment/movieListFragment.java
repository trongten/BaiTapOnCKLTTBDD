package com.example.movierating.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.movierating.R;
import com.example.movierating.adapter.movieListAdapter;
import com.example.movierating.dao.DAO_Movie;
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

        List<Movie> movies = DB_Movie.getInstance(this.getContext()).dao_movie().getAllMovie();

        movieListAdapter movieListAdapter = new movieListAdapter(getActivity(),R.layout.activity_item_movie_list, movies);
        idListView.setAdapter(movieListAdapter);
        return view;
    }
}