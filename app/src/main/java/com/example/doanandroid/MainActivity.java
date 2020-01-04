package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Class.AmNhac;
import com.example.doanandroid.ManHinhDangNhap.AsyncTask_DangNhap;
import com.example.doanandroid.ManHinhDangNhap.DangKy;
import com.example.doanandroid.ManHinhDangNhap.QuenMatKhau;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //    private String asyncTaskDangNhap;
    //Tạo ds nhạc
    public static ArrayList<AmNhac> mNhac;
    int vt = 0;
    public static MediaPlayer mediaPlayer;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_dang_nhap);
        runMusic();
        mediaPlayer.start();
    }

    public void DangNhap(View view) {
        TextView txtTK, txtMK;
        String sTK = null, sMK = null;

        txtTK = findViewById(R.id.edtTenDangNhap);
        txtMK = findViewById(R.id.edtMatKhau);

        sTK = txtTK.getText().toString();
        sMK = txtMK.getText().toString();
        //Xác thực tạm

        if (sTK.isEmpty() || sMK.isEmpty()) {
            Toast.makeText(this, "Tài khoản hoặc mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
        } else if (sMK.length() < 6) {
            Toast.makeText(this, "Đ", Toast.LENGTH_SHORT).show();
        } else {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, mNhac.get(1).getFile());
            mediaPlayer.start();
            new AsyncTask_DangNhap(sTK, sMK, this).execute();
        }


    }

    public void DangKy(View view) {
        Intent intent = new Intent(this, DangKy.class);
        startActivity(intent);
    }

    public void QuenMK(View view) {
        Intent intent = new Intent(this, QuenMatKhau.class);
        startActivity(intent);
    }

    public void runMusic() {
        mNhac = new ArrayList<>();
        mNhac.add(new AmNhac(R.raw.startgame));
        mNhac.add(new AmNhac(R.raw.nen));
        mediaPlayer = MediaPlayer.create(this, mNhac.get(vt).getFile());
    }
}
