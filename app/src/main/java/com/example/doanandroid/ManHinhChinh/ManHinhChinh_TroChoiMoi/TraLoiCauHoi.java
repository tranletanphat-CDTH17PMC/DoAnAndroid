package com.example.doanandroid.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doanandroid.R;

public class TraLoiCauHoi extends AppCompatActivity {
    private String jSonDSCauHoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_hien_thi_cau_hoi);
        Intent intent = getIntent();
        jSonDSCauHoi = intent.getStringExtra("DanhSachCauHoi");
        Toast.makeText(this, jSonDSCauHoi, Toast.LENGTH_LONG).show();
    }
}
