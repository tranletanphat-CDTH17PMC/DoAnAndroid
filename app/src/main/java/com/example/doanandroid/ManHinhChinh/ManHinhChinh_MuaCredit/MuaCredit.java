package com.example.doanandroid.ManHinhChinh.ManHinhChinh_MuaCredit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanandroid.ManHinhChinh.GoiCredit;
import com.example.doanandroid.ManHinhChinh.LinhVuc;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MuaCredit extends AppCompatActivity {
    ArrayList<GoiCredit> goiCredits;
    String jSonGoiCredit;
    TextView tenGoiA,tenGoiB,tenGoiC,tenGoiD,giaTienA,giaTienB,giaTienC,giaTienD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_mua_credit);
        AnhXa();
    }
    public void AnhXa()
    {
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

        if (getListGoiCredit(jSonGoiCredit)) {
            tenGoiA.setText(goiCredits.get(0).getTenGoi());
            tenGoiB.setText(goiCredits.get(1).getTenGoi());
            tenGoiC.setText(goiCredits.get(2).getTenGoi());
            tenGoiD.setText(goiCredits.get(3).getTenGoi());
            giaTienA.setText(goiCredits.get(0).getGiaTien());
            giaTienB.setText(goiCredits.get(1).getGiaTien());
            giaTienC.setText(goiCredits.get(2).getGiaTien());
            giaTienD.setText(goiCredits.get(3).getGiaTien());
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
    public Boolean getListGoiCredit(String jSonString)
    {
        try {
            goiCredits = new ArrayList<>();
            JSONObject root = new JSONObject(jSonString);
            JSONArray jr = root.getJSONArray("data");;
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = jr.getJSONObject(i);
                GoiCredit thongTin = new GoiCredit();
                thongTin.setTenGoi(jb.getString("ten_goi"));
                thongTin.setGiaTien(jb.getString("so_tien"));
                goiCredits.add(thongTin);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
