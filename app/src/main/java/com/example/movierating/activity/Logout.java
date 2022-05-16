package com.example.movierating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movierating.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Logout extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);
        Button btnLogout = findViewById(R.id.btn_logout_logout);
        TextView tvEmail = findViewById(R.id.tv_email_logout);
        tvEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Logout.this, movieList.class));
        });
    }
}
