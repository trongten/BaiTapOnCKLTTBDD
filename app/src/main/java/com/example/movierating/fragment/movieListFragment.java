package com.example.movierating.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import com.example.movierating.R;
import com.example.movierating.adapter.movieListAdapter;
import com.example.movierating.database.DatabaseHandler;
import com.example.movierating.entity.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link movieListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class movieListFragment extends Fragment {

    private ListView idListView;
    private List<Movie> movies;
    private DatabaseReference mDatabase;
    private List<Movie> movies2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());

        setEnterTransition(inflater.inflateTransition(R.transition.slide_left));
        setExitTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDatabase = FirebaseDatabase.getInstance().getReference("Movies");

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        idListView = view.findViewById(R.id.idmovielistview);
        DatabaseHandler d = new DatabaseHandler(getContext());
        try {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Movie c = postSnapshot.getValue(Movie.class);
                        boolean flg = false;
                        for (Movie movie : d.getAllMoive()) {
                            if (c.getId()==movie.getId())
                                d.deleteMovie(c);
                                flg = true;
                        }
                        if (!flg)
                            d.updateMovie(c);
                        else
                            d.add(c);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<Movie> listMovie = d.getAllMoive();
        System.out.println(listMovie.toString());

        movieListAdapter movieListAdapter = new movieListAdapter(getActivity(), R.layout.activity_item_movie_list, listMovie);
        idListView.setAdapter(movieListAdapter);
        return view;
    }


}