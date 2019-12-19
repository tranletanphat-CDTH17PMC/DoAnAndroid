package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_QuanLiTaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_quan_ly_tai_khoan);
        txtTenTK = findViewById(R.id.txtQLTenTaiKhoan);
        txtEmail = findViewById(R.id.txtQLEmail);
        txtMatKhau = findViewById(R.id.txtQLMK);
        txtMatKhau2 = findViewById(R.id.txtQLMK2);
        Intent intent = getIntent();
        thongTinNguoiChoi = intent.getStringExtra("ThongTinNguoiChoi");
        if (getThongTinNguoiChoi(thongTinNguoiChoi)) {
            txtTenTK.setText(nguoiChoi.get(0).getTenDangNhap());
            txtEmail.setText(nguoiChoi.get(0).getEmail());
        } else {
            Toast.makeText(this, thongTinNguoiChoi, Toast.LENGTH_LONG).show();
            txtTenTK.setText("API not run");
            txtEmail.setVisibility(View.INVISIBLE);
            txtMatKhau.setVisibility(View.INVISIBLE);
            txtMatKhau.setVisibility(View.INVISIBLE);
        }

    }
    public void CapNhat(View view)
    {
        this.finish();
    }

    private Boolean getThongTinNguoiChoi(String jSonString) {
        try {
            nguoiChoi = new ArrayList();
            JSONObject root = new JSONObject(jSonString);
            JSONArray jr = root.getJSONArray("data");
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = jr.getJSONObject(i);
                NguoiChoi thongTin = new NguoiChoi();
                thongTin.setTenDangNhap(jb.getString("ten_dang_nhap"));
                thongTin.setMatKhau(jb.getString("mat_khau"));
                thongTin.setEmail(jb.getString("email"));
                thongTin.setHinhDaiDien(jb.getString("hinh_dai_dien"));
                thongTin.setDiemCaoNhat(jb.getString("diem_cao_nhat"));
                thongTin.setCredit(jb.getString("credit"));
                nguoiChoi.add(thongTin);
                return true;
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
