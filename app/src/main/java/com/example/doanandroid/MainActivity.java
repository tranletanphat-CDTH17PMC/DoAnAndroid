package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doanandroid.ManHinhDangNhap.AsyncTask_DangNhap;

public class MainActivity extends AppCompatActivity {

//    private String asyncTaskDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_dang_nhap);
    }

    public void DangNhap(View view) {
        TextView txtTK, txtMK;
        String sTK = null, sMK = null;

        txtTK = findViewById(R.id.edtTenDangNhap);
        txtMK = findViewById(R.id.edtMatKhau);

        sTK = txtTK.getText().toString();
        sMK = txtMK.getText().toString();
        //Xác thực tạm
        new AsyncTask_DangNhap(sTK, sMK, this).execute();

    }
}
