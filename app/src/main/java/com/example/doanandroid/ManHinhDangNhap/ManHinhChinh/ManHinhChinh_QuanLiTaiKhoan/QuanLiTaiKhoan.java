package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_QuanLiTaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Class.NguoiChoi;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuanLiTaiKhoan extends AppCompatActivity {
    private String thongTinNguoiChoi;
    TextView txtTenTK, txtEmail, txtMatKhau, txtMatKhau2;
    ArrayList<NguoiChoi> nguoiChoi;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_quan_ly_tai_khoan);
        txtTenTK = findViewById(R.id.txtQLTenTaiKhoan);
        txtEmail = findViewById(R.id.txtQLEmail);
        txtMatKhau = findViewById(R.id.txtQLMK);
        txtMatKhau2 = findViewById(R.id.txtQLMK2);
        String ten, email;
        sharedPreferences = getSharedPreferences("nguoiChoi", MODE_PRIVATE);
        ten = sharedPreferences.getString("ten_dang_nhap", "");
        email = sharedPreferences.getString("email", "");
        txtTenTK.setText(ten);
        txtEmail.setText(email);
//        if (getThongTinNguoiChoi(thongTinNguoiChoi)) {
//            txtTenTK.setText(nguoiChoi.get(0).getTenDangNhap());
//            txtEmail.setText(nguoiChoi.get(0).getEmail());
//        } else {
//            Toast.makeText(this, thongTinNguoiChoi, Toast.LENGTH_LONG).show();
//            txtTenTK.setText("API not run");
//            txtEmail.setVisibility(View.INVISIBLE);
//            txtMatKhau.setVisibility(View.INVISIBLE);
//            txtMatKhau.setVisibility(View.INVISIBLE);
//        }

    }

    public void CapNhat(View view) {
        this.finish();
    }

}
