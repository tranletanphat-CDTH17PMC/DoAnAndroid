package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Class.CauHoi;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class TraLoiCauHoi extends AppCompatActivity {
    private String jSonDSCauHoi;
    private ArrayList<CauHoi> mCauHoi;
    private ArrayList<String> mRandom;
    private TextView NoiDung, PhuongAnA, PhuongAnB, PhuongAnC, PhuongAnD;
    private String chon = null;
    private String PhuongAnDung = null;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_hien_thi_cau_hoi);

        NoiDung = findViewById(R.id.txtNoiDung);
        PhuongAnA = findViewById(R.id.txtPhuongAnA);
        PhuongAnB = findViewById(R.id.txtPhuongAnB);
        PhuongAnC = findViewById(R.id.txtPhuongAnC);
        PhuongAnD = findViewById(R.id.txtPhuongAnD);
        mRandom = new ArrayList<>();
        Intent intent = getIntent();
        jSonDSCauHoi = intent.getStringExtra("DanhSachCauHoi");

        HienThiCauHoi();
    }

    public int TronCauHoi() {
        random = new Random();
        int vt;
        if (mRandom.size() == 0) {
            vt = random.nextInt(mCauHoi.size());
            mRandom.add(String.valueOf(vt));
            return vt;
        }
        if (!(mRandom.size() == mCauHoi.size())) {
            do {
                vt = random.nextInt(mCauHoi.size());
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

    public void HienThiCauHoi() {
        if (getDSCauHoi(jSonDSCauHoi)) {
            int i = TronCauHoi();
            if (!(i == -1)) {
                NoiDung.setText(mCauHoi.get(i).getNoiDung());
                PhuongAnA.setText(mCauHoi.get(i).getPhuongAnA());
                PhuongAnB.setText(mCauHoi.get(i).getPhuongAnB());
                PhuongAnC.setText(mCauHoi.get(i).getPhuongAnC());
                PhuongAnD.setText(mCauHoi.get(i).getPhuongAnD());
                PhuongAnDung = mCauHoi.get(i).getPhuongAnDung();
            } else {
                Toast.makeText(this, "Hết câu hỏi rồi ba", Toast.LENGTH_SHORT).show();
            }
        } else {
            PhuongAnA.setText("API not run");
            PhuongAnB.setVisibility(View.INVISIBLE);
            PhuongAnC.setVisibility(View.INVISIBLE);
            PhuongAnD.setVisibility(View.INVISIBLE);
        }
    }

    public boolean getDSCauHoi(String jSonDSCauHoi) {
        try {
            mCauHoi = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(jSonDSCauHoi);
            JSONArray jr = jsonObject.getJSONArray("data");
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject object = jr.getJSONObject(i);
                CauHoi cauHoi = new CauHoi();
                cauHoi.setCauHoiID(Integer.parseInt(object.getString("id")));
                cauHoi.setNoiDung(object.getString("noi_dung"));
                cauHoi.setPhuongAnA(object.getString("phuong_an_a"));
                cauHoi.setPhuongAnB(object.getString("phuong_an_b"));
                cauHoi.setPhuongAnC(object.getString("phuong_an_c"));
                cauHoi.setPhuongAnD(object.getString("phuong_an_d"));
                cauHoi.setPhuongAnDung(object.getString("dap_an"));
                mCauHoi.add(cauHoi);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void ChonDapAn(View view) {
        switch (view.getId()) {
            case R.id.txtPhuongAnA: {
                HienThiCauHoi();
                break;
            }
            case R.id.txtPhuongAnB: {
                HienThiCauHoi();
                break;
            }
            case R.id.txtPhuongAnC: {
                HienThiCauHoi();
                break;
            }
            case R.id.txtPhuongAnD: {
                HienThiCauHoi();
                break;
            }
        }
    }
}
