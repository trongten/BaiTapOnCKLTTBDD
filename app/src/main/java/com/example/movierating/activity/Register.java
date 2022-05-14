package com.example.movierating.activity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movierating.R;

public class Register extends AppCompatActivity {
    //FirebaseAuth fAuth;
    EditText edTaiKhoan, edPass, edRePass, edEmail;
    Button btnRegister;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        edTaiKhoan = (EditText) findViewById(R.id.ed_Register_Taikhoan);
        edPass = (EditText) findViewById(R.id.ed_Register_Password);
        edRePass = (EditText) findViewById(R.id.ed_Register_RePassword);
        edEmail= (EditText) findViewById(R.id.ed_Register_email);

        btnRegister = (Button) findViewById(R.id.btn_register_register);

        //fAuth=FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dangky();
            }
        });
    }

    private void Dangky() {
        String email = edEmail.getText().toString().trim();
        String taikhoan = edTaiKhoan.getText().toString().trim();
        String Password = edPass.getText().toString().trim();
        String confirmPass = edRePass.getText().toString().trim();

        if (taikhoan.isEmpty()) {
            edTaiKhoan.setError("Name must be filled");
            edTaiKhoan.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            edEmail.setError("Email must be filled");
            edEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Email invalid");
            edEmail.requestFocus();
            return;
        }
        if (Password.isEmpty()) {
            edPass.setError("Password must be filled");
            edPass.requestFocus();
            return;
        } else if (!Password.equals(confirmPass)) {
            edRePass.setError("Password and Re-Password must be the same");
            edRePass.requestFocus();
            return;
        } else if (Password.length() < 6) {
            edRePass.setError("Password must be more than 6 characters");
            edRePass.requestFocus();
            return;
        }
    }
}
