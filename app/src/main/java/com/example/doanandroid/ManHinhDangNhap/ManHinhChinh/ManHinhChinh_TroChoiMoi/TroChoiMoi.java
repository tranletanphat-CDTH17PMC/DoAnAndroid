package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doanandroid.Class.LinhVuc;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class TroChoiMoi extends AppCompatActivity {
    ArrayList<LinhVuc> mLinhVuc;
    private String jSonLinhVuc;
    Button btnLv1, btnLv2, btnLv3, btnLv4;
    String LinhVucID1, LinhVucID2, LinhVucID3, LinhVucID4;
    private ArrayList<String> mRandom;
    Random random;
    int vtht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_chon_linh_vuc);

        btnLv1 = findViewById(R.id.btnLinhVuc1);
        btnLv2 = findViewById(R.id.btnLinhVuc2);
        btnLv3 = findViewById(R.id.btnLinhVuc3);
        btnLv4 = findViewById(R.id.btnLinhVuc4);

        mRandom = new ArrayList<>();

        Intent intent = getIntent();
        jSonLinhVuc = intent.getStringExtra("DanhSachLinhVuc");

        if (getListLinhVuc(jSonLinhVuc)) {

            vtht = TronLinhVuc();
            LinhVucID1 = mLinhVuc.get(vtht).getLinhVucId();
            btnLv1.setText(mLinhVuc.get(vtht).getTenLinhVuc());
            vtht = TronLinhVuc();
            LinhVucID2 = mLinhVuc.get(vtht).getLinhVucId();
            btnLv2.setText(mLinhVuc.get(vtht).getTenLinhVuc());
            vtht = TronLinhVuc();
            LinhVucID3 = mLinhVuc.get(vtht).getLinhVucId();
            btnLv3.setText(mLinhVuc.get(vtht).getTenLinhVuc());
            vtht = TronLinhVuc();
            LinhVucID4 = mLinhVuc.get(vtht).getLinhVucId();
            btnLv4.setText(mLinhVuc.get(vtht).getTenLinhVuc());
        } else {
            btnLv1.setText("API not run");
            btnLv2.setVisibility(View.INVISIBLE);
            btnLv3.setVisibility(View.INVISIBLE);
            btnLv4.setVisibility(View.INVISIBLE);
        }
    }

    public int TronLinhVuc() {

        random = new Random();
        int vt;
        if (mRandom.size() == 0) {
            vt = random.nextInt(mLinhVuc.size());
            mRandom.add(String.valueOf(vt));
            return vt;
        }
        if (!(mRandom.size() == mLinhVuc.size())) {
            do {
                vt = random.nextInt(mLinhVuc.size());
            } while (SoSanhSoVaMang(vt));
            mRandom.add(String.valueOf(vt));
            return vt;
        }
        return -1;
    }

    public boolean SoSanhSoVaMang(int vt) {
        for (int i = 0; i < mRandom.size(); i++) {
            if (String.valueOf(vt).equals(mRandom.get(i))) {
                return true;
            }
        }
        return false;
    }

    public Boolean getListLinhVuc(String jSonString) {
        try {
            mLinhVuc = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(jSonString);
            JSONArray jr = jsonObject.getJSONArray("data");

            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject object = jr.getJSONObject(i);
                LinhVuc thongTin = new LinhVuc();
                thongTin.setTenLinhVuc(object.getString("ten_linh_vuc"));
                thongTin.setLinhVucId(object.getString("id"));
                mLinhVuc.add(thongTin);
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
                new AsyncTask_LoadCauHoi(this).execute(LinhVucID2);
                break;
            }
            case R.id.btnLinhVuc3: {
                new AsyncTask_LoadCauHoi(this).execute(LinhVucID3);
                break;
            }
            case R.id.btnLinhVuc4: {
                new AsyncTask_LoadCauHoi(this).execute(LinhVucID4);
                break;
            }
        }
    }

    public void Back(View view) {
        this.finish();
    }
}
