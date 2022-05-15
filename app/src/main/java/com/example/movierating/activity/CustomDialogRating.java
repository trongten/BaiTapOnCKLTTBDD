package com.example.movierating.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.movierating.R;

public class CustomDialogRating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog_rating);
        RatingBar rt = findViewById(R.id.ratingBar123);


    }
}