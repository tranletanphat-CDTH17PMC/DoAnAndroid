package com.example.doanandroid.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.ManHinhChinh.LinhVuc;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TroChoiMoi extends AppCompatActivity {
    ArrayList<LinhVuc> linhVucs;
    private String jSonLinhVuc;
    Button btnLv1, btnLv2, btnLv3, btnLv4;
    String LinhVucID1, LinhVucID2, LinhVucID3, LinhVucID4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_chon_linh_vuc);

        btnLv1 = findViewById(R.id.btnLinhVuc1);
        btnLv2 = findViewById(R.id.btnLinhVuc2);
        btnLv3 = findViewById(R.id.btnLinhVuc3);
        btnLv4 = findViewById(R.id.btnLinhVuc4);


        Intent intent = getIntent();
        jSonLinhVuc = intent.getStringExtra("DanhSachLinhVuc");

        if (getListLinhVuc(jSonLinhVuc)) {

            LinhVucID1 = linhVucs.get(0).getLinhVucId();
            btnLv1.setText(linhVucs.get(0).getTenLinhVuc());

            LinhVucID2 = linhVucs.get(0).getLinhVucId();
            btnLv2.setText(linhVucs.get(1).getTenLinhVuc());

            LinhVucID3 = linhVucs.get(0).getLinhVucId();
            btnLv3.setText(linhVucs.get(2).getTenLinhVuc());

            LinhVucID4 = linhVucs.get(0).getLinhVucId();
            btnLv4.setText(linhVucs.get(3).getTenLinhVuc());
        } else {
            btnLv1.setText("API not run");
            btnLv2.setVisibility(View.INVISIBLE);
            btnLv3.setVisibility(View.INVISIBLE);
            btnLv4.setVisibility(View.INVISIBLE);
        }
    }

    public Boolean getListLinhVuc(String jSonString) {
        try {
            linhVucs = new ArrayList<>();
            JSONObject root = new JSONObject(jSonString);
            JSONArray jr = root.getJSONArray("data");
            ;
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = jr.getJSONObject(i);
                LinhVuc thongTin = new LinhVuc();
                thongTin.setTenLinhVuc(jb.getString("ten_linh_vuc"));
                thongTin.setLinhVucId(jb.getString("id"));
                linhVucs.add(thongTin);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void ChonLinhVuc_Click(View view) {

        switch (view.getId()) {
            case R.id.btnLinhVuc1: {
                new AsyncTask_LoadCauHoi(this).execute(LinhVucID1);
                break;
            }
            case R.id.btnLinhVuc2: {
                Toast.makeText(this, "Đây là lĩnh vực 2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnLinhVuc3: {
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnLinhVuc4: {
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
