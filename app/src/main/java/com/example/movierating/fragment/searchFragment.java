package com.example.movierating.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;


import com.example.movierating.R;
import com.example.movierating.adapter.movieListAdapter;
import com.example.movierating.database.DB_Movie;
import com.example.movierating.database.DatabaseHandler;
import com.example.movierating.entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link movieListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class searchFragment extends Fragment {

    private ListView idListView;
    private EditText te;
    private DB_Movie db_movie;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        idListView = view.findViewById(R.id.idmovielistview2);
        db_movie = DB_Movie.getInMemoryDatabase(getContext());
        DatabaseHandler d = new DatabaseHandler(getContext());

        List<Movie>  movies = d.getAllMoive();

        movieListAdapter movieListAdapter = new movieListAdapter(getActivity(),R.layout.activity_item_movie_list, movies);
        movieListAdapter.setFilterBySearch("KoCoPhomAsdw");
        idListView.setAdapter(movieListAdapter);

        te = view.findViewById(R.id.edtSearchMovieName);

        te.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                movieListAdapter.setFilterBySearch(editable.toString().toLowerCase(Locale.ROOT));
            }
        });


        return view;
    }
}