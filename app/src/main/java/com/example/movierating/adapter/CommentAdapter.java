package com.example.movierating.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.movierating.R;
import com.example.movierating.entity.Rate;

import java.util.List;

public class CommentAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Rate> arrayList;
    private int positionSelect = -1;

    public CommentAdapter(Context context, int layout, List<Rate> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
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

        TextView txtemail = view.findViewById(R.id.txtEmail);
        TextView txtcmt = view.findViewById(R.id.txtCMT);
        TextView txtrate = view.findViewById(R.id.txtRating);

        Rate rt = arrayList.get(i);

        txtemail.setText(rt.getUser());
        txtcmt.setText(rt.getComment());
        txtrate.setText(String.valueOf(rt.getRating()));



        return view;
    }



}
