package com.example.movierating.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movierating.R;

public class Login extends AppCompatActivity {
    //FirebaseAuth fAuth;
    EditText edTaiKhoan;
    EditText edPass;
    Button btnLogin;
    TextView tvQuenmatkhau, tvKhongCanTaiKhoan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edTaiKhoan = (EditText) findViewById(R.id.ed_Login_Taikhoan);
        edPass = (EditText) findViewById(R.id.ed_Login_Password);
        tvQuenmatkhau = (TextView) findViewById(R.id.tvQuenmatkhau);
        tvKhongCanTaiKhoan = (TextView) findViewById(R.id.tv_khongcanDangNhap);
        btnLogin = (Button) findViewById(R.id.btn_Login_Login);

        //fAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap();
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

}
