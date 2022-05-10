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

import java.util.ArrayList;
import java.util.List;

public class movieListAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Movie> arrayList;
    List<Movie> arrayFilter;
    private int positionSelect = -1;

    public movieListAdapter(Context context, int layout, List<Movie> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
        arrayFilter= arrayList;
    }



    @Override
    public int getCount() {
        return  arrayFilter.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        }
        //List movie
        TextView tvName= view.findViewById(R.id.idMovieName);
        TextView tvtl= view.findViewById(R.id.idTL);
        ImageView imgTv=  view.findViewById(R.id.idImgMovie);
        RatingBar rt = view.findViewById(R.id.rating);

        Movie movie = arrayFilter.get(i);

        tvName.setText(movie.getMovieName());
        tvtl.setText(movie.getDescription());
        imgTv.setImageResource(movie.getId());
        rt.setRating(Float.parseFloat(String.valueOf(movie.getRating())));

        //Viet Intent vao day
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, movieDetail.class);
                i.putExtra("name",movie.getMovieName());
                i.putExtra("description",movie.getDescription());
                i.putExtra("rating",movie.getRating());
                i.putExtra("img",movie.getLinkImg());
                i.putExtra("trailer",movie.getLinkTrailer());
                context.startActivity(i);
            }
        });
        return view;
    }


    public void setFilter(String filter) {
        arrayFilter = new ArrayList<>();
        System.out.println(filter);
        for(Movie movie: arrayList) {
            //indexOf: tim chuoi trong chuoi: thanh cong (123) => -1
            if(movie.getMovieName().toLowerCase().indexOf(filter) != -1) {
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
