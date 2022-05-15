package com.example.movierating.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movierating.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edTaiKhoan;
    EditText edPass;
    Button btnLogin, btnLogout;
    TextView tvQuenmatkhau, tvregister;
    static FirebaseUser firebaseUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        edTaiKhoan = (EditText) findViewById(R.id.ed_Login_Taikhoan);
        edPass = (EditText) findViewById(R.id.ed_Login_Password);
        tvQuenmatkhau = (TextView) findViewById(R.id.tvQuenmatkhau);
        tvregister = (TextView) findViewById(R.id.tvregister);
        btnLogin = (Button) findViewById(R.id.btn_Login_Login);
        btnLogout = findViewById(R.id.btnLogout);
        firebaseUser=mAuth.getCurrentUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap();
                login(edTaiKhoan.getText().toString(),edPass.getText().toString());
            }
        });
        //-----==============---------//
        if(firebaseUser==null)
            btnLogout.setVisibility(View.GONE);
        else
            btnLogout.setVisibility(View.VISIBLE);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                startActivity(new Intent(Login.this, movieList.class));
            }
        });
        //-----==============---------//
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),Register.class);
                startActivity(i);
            }
        });
    }

    private void DangNhap() {
        String taikhoan = edTaiKhoan.getText().toString().trim();
        String pass = edPass.getText().toString().trim();

        if (taikhoan.isEmpty()) {
            edTaiKhoan.setError("Tai Khoan must be filled");
            edTaiKhoan.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            edPass.setError("Password must be filled");
            edPass.requestFocus();
            return;
        }

    }

    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent i  = new Intent(getBaseContext(),movieList.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }


    private void updateUI(FirebaseUser user) {
        if(user != null){
            Toast.makeText(Login.this, "signInWithEmail:success",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Login.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }



}
