package com.example.movierating.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.movierating.R;
import com.example.movierating.activity.movieDetail;
import com.example.movierating.entity.Movie;
import com.example.movierating.other.ImageLoadTask;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.List;

public class movieListAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Movie> arrayList;
    List<Movie> arrayFilter;
    private int positionSelect = -1;
    String API_KEY = "AIzaSyDekrO-eHzhP4bfsRdFDuD_87ccxiXhxbU";

    public movieListAdapter(Context context, int layout, List<Movie> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
        arrayFilter = arrayList;
    }

    @Override
    public int getCount() {
        return arrayFilter.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayFilter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayFilter.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        }
        //List movie
        TextView tvName = view.findViewById(R.id.tvwName_itemMovieList);
        TextView tvtl = view.findViewById(R.id.tvNam_itemMovieList);
        ImageView imgTv = view.findViewById(R.id.idImgMovie);

        RatingBar rt = view.findViewById(R.id.rating);

        Movie movie = arrayFilter.get(i);

        tvName.setText(movie.getMovieName());
        tvtl.setText(String.valueOf(movie.getYear()));
        new ImageLoadTask(movie.getLinkImg(), imgTv).execute();
        rt.setRating(Float.parseFloat(String.valueOf(movie.getRating())));

        TextView tvDescription = view.findViewById(R.id.tvDescription_itemMovieList);
        if (tvDescription != null) {
            tvDescription.setText(movie.getDescription());
        }

        //Viet Intent vao day
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, movieDetail.class);
                i.putExtra("id",movie.getId());
                i.putExtra("name", movie.getMovieName());
                i.putExtra("description", movie.getDescription());
                i.putExtra("rating",movie.getRating());
                i.putExtra("img", movie.getLinkImg());
                i.putExtra("trailer", movie.getLinkTrailer());
                i.putExtra("year", movie.getYear());

                context.startActivity(i);
            }
        });
        return view;
    }


    public void setFilter(String filter) {
        arrayFilter = new ArrayList<>();
        System.out.println(filter);
        for (Movie movie : arrayList) {
            //indexOf: tim chuoi trong chuoi: thanh cong (123) => -1
            if (movie.getMovieName().toLowerCase().indexOf(filter) != -1) {
                arrayFilter.add(movie);
            }
        }
        System.out.println(arrayFilter);

        notifyDataSetChanged();
    }

    public void setFilterBySearch(String giaTriInPut) {
        //123
        setFilter(giaTriInPut);
    }

    public void setAllMovie() {
        arrayFilter = arrayList;
        notifyDataSetChanged();
    }

}
