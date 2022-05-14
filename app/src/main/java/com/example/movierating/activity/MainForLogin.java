package com.example.movierating.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.movierating.R;

public class MainForLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_for_login);

        Button butnLogin = findViewById(R.id.btnToLogin);
        butnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  =  new Intent(getBaseContext(), Login.class);
                startActivity(i);
            }
        });

    }

}