package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.BangXepHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.doanandroid.Adapter.BangXepHangAdapter;
import com.example.doanandroid.Adapter.LichSuChoiAdapter;
import com.example.doanandroid.Class.NguoiChoi;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BangXepHang extends AppCompatActivity {
    private String jsonNguoiChoi;
    private ArrayList<NguoiChoi> mNguoiChoi;
    private RecyclerView mRecyclerView;
    private BangXepHangAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_bang_xep_hang);
        Intent intent = getIntent();
        jsonNguoiChoi = intent.getStringExtra("DSNguoiChoi");

        if (getThongTinNguoiChoi(jsonNguoiChoi)) {
            //Khởi tạo recycler view
            mRecyclerView = findViewById(R.id.rvXepHang);
            //Khởi tạo adapter và thiết lập nó cho recycler view
            mAdapter = new BangXepHangAdapter(this, mNguoiChoi);
            mRecyclerView.setAdapter(mAdapter);
            //Thiết lập giao diện cho recycler view
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private Boolean getThongTinNguoiChoi(String jSonString) {
        try {
            mNguoiChoi = new ArrayList<>();
            JSONObject root = new JSONObject(jSonString);
            JSONArray jr = root.getJSONArray("data");
            ;
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = jr.getJSONObject(i);
                NguoiChoi thongTin = new NguoiChoi();
                thongTin.setID(jb.getString("id"));
                thongTin.setTenDangNhap(jb.getString("ten_dang_nhap"));
                thongTin.setMatKhau(jb.getString("mat_khau"));
                thongTin.setEmail(jb.getString("email"));
                thongTin.setHinhDaiDien(jb.getString("hinh_dai_dien"));
                thongTin.setDiemCaoNhat(jb.getString("diem_cao_nhat"));
                thongTin.setCredit(jb.getString("credit"));
                mNguoiChoi.add(thongTin);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void Back(View view) {
        this.finish();
    }
}
