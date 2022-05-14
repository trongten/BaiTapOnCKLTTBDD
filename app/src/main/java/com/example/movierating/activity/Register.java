package com.example.movierating.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edTaiKhoan, edPass, edRePass, edEmail;
    Button btnRegister;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        edTaiKhoan = (EditText) findViewById(R.id.ed_Register_Taikhoan);
        edPass = (EditText) findViewById(R.id.ed_Register_Password);
        edRePass = (EditText) findViewById(R.id.ed_Register_RePassword);
        TextView tvlogin = findViewById(R.id.tvlogin);

        btnRegister = (Button) findViewById(R.id.btn_register_register);

        //fAuth=FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dangky();
                createAccount(edTaiKhoan.getText().toString(),edPass.getText().toString());
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),Login.class);
                startActivity(i);
            }
        });
    }

    public void createAccount(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i  = new Intent(getBaseContext(),Login.class);
                            startActivity(i);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }


    private void updateUI(FirebaseUser user) {
        if(user != null){
            Toast.makeText(Register.this, "createUserWithEmail:success",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Register.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void Dangky() {

        String taikhoan = edTaiKhoan.getText().toString().trim();
        String Password = edPass.getText().toString().trim();
        String confirmPass = edRePass.getText().toString().trim();


        if (taikhoan.isEmpty()) {
            edEmail.setError("Email must be filled");
            edEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(taikhoan).matches()) {
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
