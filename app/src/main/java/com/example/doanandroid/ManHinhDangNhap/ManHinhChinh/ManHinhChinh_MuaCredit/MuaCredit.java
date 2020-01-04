package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_MuaCredit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Class.GoiCredit;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MuaCredit extends AppCompatActivity {
    private ArrayList<GoiCredit> mCredit;
    private String jSonGoiCredit;
    private TextView tenGoiA, tenGoiB, tenGoiC, tenGoiD, giaTienA, giaTienB, giaTienC, giaTienD;
    private TextView txtTen, txtCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_mua_credit);

        txtTen = findViewById(R.id.txtTenTaiKhoan);
        txtCredit = findViewById(R.id.txtCredit);

        String ten, credit;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("nguoiChoi", MODE_PRIVATE);
        ten = sharedPreferences.getString("ten_dang_nhap", "");
        credit = sharedPreferences.getString("credit", "");

        txtTen.setText(ten);
        txtCredit.setText(credit);

        tenGoiA = findViewById(R.id.txtGoiA);
        tenGoiB = findViewById(R.id.txtGoiB);
        tenGoiC = findViewById(R.id.txtGoiC);
        tenGoiD = findViewById(R.id.txtGoiD);
        giaTienA = findViewById(R.id.txtGiaTienA);
        giaTienB = findViewById(R.id.txtGiaTienB);
        giaTienC = findViewById(R.id.txtGiaTienC);
        giaTienD = findViewById(R.id.txtGiaTienD);


        Intent intent = getIntent();
        jSonGoiCredit = intent.getStringExtra("DanhSachGoiCredit");

//        Toast.makeText(this, jSonGoiCredit, Toast.LENGTH_LONG).show();

        HienThiGoiCredit();
    }

    @SuppressLint("SetTextI18n")
    public void HienThiGoiCredit() {

        if (getListGoiCredit(jSonGoiCredit)) {
            tenGoiA.setText(mCredit.get(0).getTenGoi());
            tenGoiB.setText(mCredit.get(1).getTenGoi());
            tenGoiC.setText(mCredit.get(2).getTenGoi());
            tenGoiD.setText(mCredit.get(3).getTenGoi());

            giaTienA.setText("$" + mCredit.get(0).getGiaTien());
            giaTienB.setText("$" + mCredit.get(1).getGiaTien());
            giaTienC.setText("$" + mCredit.get(2).getGiaTien());
            giaTienD.setText("$" + mCredit.get(3).getGiaTien());
        } else {
            tenGoiA.setText("API not run");
            tenGoiB.setVisibility(View.INVISIBLE);
            tenGoiC.setVisibility(View.INVISIBLE);
            tenGoiD.setVisibility(View.INVISIBLE);
            giaTienA.setText("API not run");
            giaTienB.setVisibility(View.INVISIBLE);
            giaTienC.setVisibility(View.INVISIBLE);
            giaTienD.setVisibility(View.INVISIBLE);
        }
    }

    public Boolean getListGoiCredit(String jSonString) {
        try {
            mCredit = new ArrayList<>();
            JSONObject root = new JSONObject(jSonString);
            JSONArray jr = root.getJSONArray("data");
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = jr.getJSONObject(i);
                GoiCredit thongTin = new GoiCredit();
                thongTin.setTenGoi(jb.getString("ten_goi"));
                thongTin.setGiaTien(jb.getString("so_tien"));
                mCredit.add(thongTin);
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
