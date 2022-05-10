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

import com.example.movierating.R;
import com.example.movierating.adapter.movieListAdapter;
import com.example.movierating.entity.Movie;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link movieListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class searchFragment extends Fragment {

    private ListView idListView;
    private EditText te;

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

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Phim Hay","tv-series","","", 7.0, 2021));
        movies.add(new Movie("Phim Hay","tv-series","","",4.0, 2021));
        movies.add(new Movie("Phim Hay","tv-series","","",3.0, 2021));
        movies.add(new Movie("Phim Hay","tv-series","","",4.5, 2021));
        movies.add(new Movie("Phim Hay","tv-series","","",4.0, 2021));

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
                movieListAdapter.setFilterBySearch(editable.toString());
            }
        });


        return view;
    }
}