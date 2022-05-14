package com.example.movierating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movierating.R;
import com.example.movierating.other.ImageLoadTask;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class movieDetail extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView youTubePlayerView;
    TextView tvwName, tvwYear, tvwDescription, tvwrating;
    ImageView ivImg;
    String API_KEY = "AIzaSyDekrO-eHzhP4bfsRdFDuD_87ccxiXhxbU";
    int REQUEST_VIDEO = 123;
    private String trailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie_detail);

        tvwName = findViewById(R.id.tvwName_Detail);
        tvwDescription = findViewById(R.id.tvwDescription_Detail);
        tvwYear = findViewById(R.id.tvwYear_Detail);
        tvwrating = findViewById(R.id.tvwRating_Detail);
        ivImg = findViewById(R.id.imgImg_Detail);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String description = i.getStringExtra("description");
        double rating = i.getDoubleExtra("rating", 0);
        trailer = i.getStringExtra("trailer");
        String img = i.getStringExtra("img");
        int year = i.getIntExtra("year", 0);

        new ImageLoadTask(img, ivImg).execute();
        tvwYear.setText(String.valueOf(year));
        tvwName.setText(name);
        tvwDescription.setText(description);
        tvwrating.setText(String.valueOf(rating));


        youTubePlayerView = findViewById(R.id.vidTrialer);
        youTubePlayerView.initialize(API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(trailer);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(movieDetail.this, REQUEST_VIDEO);

        } else
            Toast.makeText(this, "ERROR!!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO) {
            youTubePlayerView.initialize(API_KEY, movieDetail.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}