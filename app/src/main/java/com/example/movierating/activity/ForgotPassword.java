package com.example.movierating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.movierating.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword extends AppCompatActivity {
    String email;
    ConstraintLayout main_content;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        Button btnDoiMk = findViewById(R.id.btnTiepTucDangNhap);

        main_content = findViewById(R.id.layout_forgotPassword);
        mAuth = FirebaseAuth.getInstance();

        Intent i = getIntent();
        email = i.getStringExtra("email_confirm").trim();

        TextView tvtile = findViewById(R.id.tvwTiltle_forgot_password);
        tvtile.setText("Chúng tôi đã gửi email đên "+email+" để thay đổi mật khẩu của bạn vui lòng kiểm tra và thay đổi mật khẩu mới");
        resetpasswoord();
        btnDoiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this,Login.class));
            }
        });
    }

    private void resetpasswoord() {
        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(
                unused -> {
                    Snackbar snackbar_su = Snackbar
                            .make(main_content, "Thay đổi password thành công cho " + email, Snackbar.LENGTH_LONG);
                    snackbar_su.show();
                }
        ).addOnFailureListener(e -> {
            Snackbar snackbar_fail = Snackbar
                    .make(main_content, "Ủa gì ợ? ủa gì ợ? Pass bị sao á", Snackbar.LENGTH_LONG);
            snackbar_fail.show();
        });

    }
}
