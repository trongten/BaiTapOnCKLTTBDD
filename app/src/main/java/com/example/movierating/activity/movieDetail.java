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

import androidx.annotation.NonNull;

import com.example.movierating.R;
import com.example.movierating.adapter.CommentAdapter;
import com.example.movierating.adapter.movieListAdapter;
import com.example.movierating.database.DatabaseHandler;
import com.example.movierating.entity.Movie;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class movieDetail extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private DatabaseReference mDatabase, mDatabaseMovie;
    private FirebaseAuth mAuth;
    YouTubePlayerView youTubePlayerView;
    TextView tvwName, tvwYear, tvwDescription, tvwrating;
    ImageView ivImg;
    ImageButton ivback;
    String API_KEY = "AIzaSyDekrO-eHzhP4bfsRdFDuD_87ccxiXhxbU";
    int REQUEST_VIDEO = 123;
    private String trailer;
    ImageButton btnSend;
    int idMovie;
    private ArrayList<Rate> listcm;
    private ListView idListView;
    double ratingtemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie_detail);
        mDatabase = FirebaseDatabase.getInstance().getReference("Review");
        mDatabaseMovie = FirebaseDatabase.getInstance().getReference("Movies");
        mAuth = FirebaseAuth.getInstance();
        tvwName = findViewById(R.id.tvwName_Detail);
        tvwDescription = findViewById(R.id.tvwDescription_Detail);
        tvwYear = findViewById(R.id.tvwYear_Detail);
        tvwrating = findViewById(R.id.tvwRating_Detail);
        ivImg = findViewById(R.id.imgImg_Detail);
        ivback = findViewById(R.id.iwBack_detail);

        Intent i = getIntent();
        idMovie = i.getIntExtra("id", 0);
        String name = i.getStringExtra("name");
        String description = i.getStringExtra("description");
        double myrate = i.getDoubleExtra("rating", 0);
        DecimalFormat format = new DecimalFormat("#.#");
        String rating = format.format(myrate);

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
        mDatabase.child(String.valueOf(idMovie))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        TextView tvwNumComments = findViewById(R.id.tvwNumberOfComments);
                        tvwNumComments.setText(" "+String.valueOf(snapshot.getChildrenCount()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        ivback.setOnClickListener(view -> {
            startActivity(new Intent(movieDetail.this, movieList.class));
        });

        btnSend = findViewById(R.id.btnComment);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Login.firebaseUser != null) {
                    Dialog dialog = new Dialog(movieDetail.this);
                    dialog.setTitle("Rating");
                    dialog.setContentView(R.layout.activity_custom_dialog_rating);
                    dialog.show();
                    RatingBar rt = dialog.findViewById(R.id.ratingBar123);
                    rt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                            if (v < 1.0f) {

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
                                    .setValue(new Rate(idMovie, rt.getRating(), editCMT.getText().toString(), mAuth.getCurrentUser().getEmail()));
                            dialog.dismiss();
                            ratingtemp = 0;
                            mDatabase.child(String.valueOf(idMovie))
                                    .addListenerForSingleValueEvent(new ValueEventListener() {

                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dataSnapshot : snapshot.getChildren())
                                                ratingtemp += dataSnapshot.child("rating").getValue(Double.class);
                                            mDatabaseMovie.child(String.valueOf(idMovie)).child("rating")
                                                    .setValue(ratingtemp / snapshot.getChildrenCount());

                                            double r = ratingtemp / snapshot.getChildrenCount();
                                            DatabaseHandler d = new DatabaseHandler(getBaseContext());
                                            d.setRating(idMovie, r);

                                            tvwrating.setText(String.valueOf(format.format(r)));

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }


                    });

                } else {
                    //neu chua login thi login
                    Intent i = new Intent(getBaseContext(), MainForLogin.class);
                    startActivity(i);
                }
            }
        });


    }


    public void laydc() {

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<HashMap<String, Rate>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Rate>>() {
                };
                Map<String, Rate> objectHashMap = dataSnapshot.child(String.valueOf(idMovie)).getValue(objectsGTypeInd);

                try {
                    Set<Map.Entry<String, Rate>> setHashMap = objectHashMap.entrySet();
                    for (Map.Entry<String, Rate> i : setHashMap) {
                        System.out.println(i.getKey() + "   -->   " + i.getValue());
                        Rate rt = i.getValue();
                        listcm.add(rt);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
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