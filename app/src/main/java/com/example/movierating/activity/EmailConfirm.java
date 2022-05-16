package com.example.movierating.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.movierating.R;

public class EmailConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirm);

        Button btnnext = findViewById(R.id.btnNext_email_confirm);
        Button btnback = findViewById(R.id.btnBack_email_confirm);
        EditText edtEmail = findViewById(R.id.edtEmail_email_confirm);
        btnback.setOnClickListener(view -> {
            startActivity(new Intent(EmailConfirm.this, Login.class));
        });
        btnnext.setOnClickListener(view -> {
            Intent i = new Intent(EmailConfirm.this, ForgotPassword.class);
            i.putExtra("email_confirm", edtEmail.getText().toString());
            startActivity(i);
        });
    }
}