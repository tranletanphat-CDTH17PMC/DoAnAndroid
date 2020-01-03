package com.example.doanandroid.ManHinhDangNhap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.doanandroid.R;

public class QuenMatKhau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_quen_mat_khau);
    }
    public void Back(View view)
    {
        finish();
    }
}
