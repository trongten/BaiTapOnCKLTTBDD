package com.example.movierating.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movierating.R;
import com.example.movierating.adapter.CommentAdapter;
import com.example.movierating.entity.Rate;
import com.example.movierating.other.ImageLoadTask;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class movieDetail extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    YouTubePlayerView youTubePlayerView;
    TextView tvwName, tvwYear, tvwDescription, tvwrating;
    ImageView ivImg;
    String API_KEY = "AIzaSyDekrO-eHzhP4bfsRdFDuD_87ccxiXhxbU";
    int REQUEST_VIDEO = 123;
    private String trailer;
    ImageButton btnCM;
    int idMovie;
    private ArrayList<Rate> listcm;
    private ListView idListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie_detail);

        mDatabase = FirebaseDatabase.getInstance().getReference("Review");
        mAuth = FirebaseAuth.getInstance();
        tvwName = findViewById(R.id.tvwName_Detail);
        tvwDescription = findViewById(R.id.tvwDescription_Detail);
        tvwYear = findViewById(R.id.tvwYear_Detail);
        tvwrating = findViewById(R.id.tvwRating_Detail);
        ivImg = findViewById(R.id.imgImg_Detail);


        Intent i = getIntent();
        idMovie = i.getIntExtra("id",0);
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

        //Comment
        EditText editCMT = findViewById(R.id.edtYourComment_Detail);

        //load comment
        listcm = new ArrayList<>();
        laydc();

        idListView = findViewById(R.id.listComments_Detail);
        CommentAdapter adapter = new CommentAdapter(getBaseContext(), R.layout.activity_comment_detail, listcm);
        idListView.setAdapter(adapter);

        //load comment

        youTubePlayerView = findViewById(R.id.vidTrialer);
        youTubePlayerView.initialize(API_KEY, this);

        btnCM = findViewById(R.id.btnComment);
        btnCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Login.firebaseUser!=null){
                    Dialog dialog = new Dialog(movieDetail.this);
                    dialog.setTitle("Rating");
                    dialog.setContentView(R.layout.activity_custom_dialog_rating);
                    dialog.show();
                    RatingBar rt = dialog.findViewById(R.id.ratingBar123);
                    rt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                            if(v<1.0f){

                                ratingBar.setRating(1.0f);
                            }
                        }
                    });

                    Button btnok = dialog.findViewById(R.id.btnok);
                    btnok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Day du lieu len firebase
                            mDatabase.child(String.valueOf(idMovie)).child(mAuth.getCurrentUser().getUid())
                                    .setValue(new Rate(idMovie,mAuth.getCurrentUser().getEmail(),editCMT.getText().toString(),rt.getRating()));
                                    dialog.dismiss();
                        }


                    });

                } else{
                    //neu chua login thi login
                    Intent i = new Intent(getBaseContext(),MainForLogin.class);
                    startActivity(i);
                }
            }
        });

    }


    public  void laydc(){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String c = (String) postSnapshot.child(String.valueOf(idMovie)).getValue();
                    System.out.println(c);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
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