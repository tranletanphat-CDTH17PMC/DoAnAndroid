package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Class.CauHoi;
import com.example.doanandroid.Class.DongHo;
import com.example.doanandroid.R;
import com.github.mikephil.charting.charts.BarChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class TraLoiCauHoi extends AppCompatActivity {
    private String jSonDSCauHoi;
    CauHoi cauHoi;
    private ArrayList<CauHoi> mCauHoi;
    private ArrayList<String> mRandom;
    private TextView NoiDung, PhuongAnA, PhuongAnB, PhuongAnC, PhuongAnD, DiemSo, CauHoiSo;
    private int diem = 0;
    private int soCau = 1;
    private boolean DoiCauHoi = true;
    private boolean TroGiup50 = true;
    private boolean TroGiupGoi = true;
    private int vtht;
    private Random random;
    int n = 999;
    int temp = 0;
    DongHo dongHo;
    ProgressBar mDongHo;
    BarChart barChart;
    int vtRandom1, vtRandom2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_hien_thi_cau_hoi);

        mDongHo = findViewById(R.id.ProBarDongHo);
        NoiDung = findViewById(R.id.txtNoiDung);

        DiemSo = findViewById(R.id.txtDiemSo);

        CauHoiSo = findViewById(R.id.txtCauHoiSo);

        PhuongAnA = findViewById(R.id.txtPhuongAnA);
        PhuongAnB = findViewById(R.id.txtPhuongAnB);
        PhuongAnC = findViewById(R.id.txtPhuongAnC);
        PhuongAnD = findViewById(R.id.txtPhuongAnD);
        dongHo = new DongHo(mDongHo, this);
        //
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
        TroGiup50 = true;
        temp = 0;
        PhuongAnA.setVisibility(View.VISIBLE);
        PhuongAnB.setVisibility(View.VISIBLE);
        PhuongAnC.setVisibility(View.VISIBLE);
        PhuongAnD.setVisibility(View.VISIBLE);
        if (DoiCauHoi) {
            CauHoiSo.setText(soCau + "");
            DiemSo.setText("Điểm số: " + diem);
        } else {
            CauHoiSo.setText(soCau + "");
            DiemSo.setText("Điểm số: " + diem);

            diem = diem + 10;
            soCau = soCau + 1;
        }
        dongHo.cancel(true);
        startDongHo();
        if (getDSCauHoi(jSonDSCauHoi)) {
            PhuongAnA.setBackgroundResource(R.drawable.mau_cauhoi);
            PhuongAnB.setBackgroundResource(R.drawable.mau_cauhoi);
            PhuongAnC.setBackgroundResource(R.drawable.mau_cauhoi);
            PhuongAnD.setBackgroundResource(R.drawable.mau_cauhoi);
            vtht = TronCauHoi();
            if (!(vtht == -1)) {
                cauHoi = mCauHoi.get(vtht);
                cauHoi.TronDapAn();
                NoiDung.setText(cauHoi.getNoiDung());
                PhuongAnA.setText(cauHoi.getPhuongAnA());
                PhuongAnB.setText(cauHoi.getPhuongAnB());
                PhuongAnC.setText(cauHoi.getPhuongAnC());
                PhuongAnD.setText(cauHoi.getPhuongAnD());
            } else {
                Toast.makeText(this, "Bạn đã hoàn thành lĩnh vực này", Toast.LENGTH_SHORT).show();
                stopDongHo();
                this.finish();
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
                cauHoi = new CauHoi();
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

    public void LuaChon(boolean th) {
        if (th) {
            new CountDownTimer(1000, 100) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    HienThiDapAnDung();
                    new CountDownTimer(500, 100) {

                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            HienThiCauHoi();
                        }
                    }.start();
                }
            }.start();
        } else {
            Toast.makeText(TraLoiCauHoi.this, "Bạn đã trả lời sai", Toast.LENGTH_SHORT).show();
            new CountDownTimer(1000, 100) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                    HienThiDapAnDung();
                    new CountDownTimer(500, 100) {

                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {

                            stopDongHo();
                            finish();
                        }
                    }.start();
                }
            }.start();

        }
    }

    public void ChonDapAn(View view) {
        boolean th = true;
        switch (view.getId()) {
            case R.id.txtPhuongAnA: {
                final String DapAn = "A";
                PhuongAnA.setBackgroundResource(R.drawable.mau_chon);
                if (KiemTraDapAn(DapAn)) {
                    LuaChon(th);
                } else {
                    th = false;
                    LuaChon(th);
                }
                break;
            }
            case R.id.txtPhuongAnB: {

                final String DapAn = "B";
                PhuongAnB.setBackgroundResource(R.drawable.mau_chon);
                if (KiemTraDapAn(DapAn)) {
                    LuaChon(th);
                } else {
                    th = false;
                    LuaChon(th);
                }
                break;
            }
            case R.id.txtPhuongAnC: {
                final String DapAn = "C";
                PhuongAnC.setBackgroundResource(R.drawable.mau_chon);
                if (KiemTraDapAn(DapAn)) {
                    LuaChon(th);
                } else {
                    th = false;
                    LuaChon(th);
                }
                break;
            }
            case R.id.txtPhuongAnD: {
                final String DapAn = "D";
                PhuongAnD.setBackgroundResource(R.drawable.mau_chon);
                if (KiemTraDapAn(DapAn)) {
                    LuaChon(th);
                } else {
                    th = false;
                    LuaChon(th);
                }
                break;
            }
        }
    }

    public boolean KiemTraDapAn(String dapAn) {
        cauHoi = mCauHoi.get(vtht);
        if (dapAn.equals(cauHoi.getPhuongAnDung())) {
            return true;
        }
        return false;
    }

    public void HienThiDapAnDung() {
        if (KiemTraDapAn("A")) {
            PhuongAnA.setBackgroundResource(R.drawable.mau_dung);
        } else if (KiemTraDapAn("B")) {
            PhuongAnB.setBackgroundResource(R.drawable.mau_dung);
        } else if (KiemTraDapAn("C")) {
            PhuongAnC.setBackgroundResource(R.drawable.mau_dung);
        } else if (KiemTraDapAn("D")) {
            PhuongAnD.setBackgroundResource(R.drawable.mau_dung);
        }
    }

    public void pause(View view) {
        stopDongHo();
        final Dialog pause = new Dialog(this);
        pause.setContentView(R.layout.pause);
        pause.setCanceledOnTouchOutside(false);
        pause.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        Button btnTiepTuc = pause.findViewById(R.id.btnTiepTuc);
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startDongHo();
                pause.dismiss();
            }
        });
        Button btnThoat = pause.findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        pause.show();
    }

    public void startDongHo() {
        dongHo = new DongHo(mDongHo, this);
        dongHo.execute(n, temp);
    }

    public void stopDongHo() {
        dongHo.cancel(true);
        temp = DongHo.k;
    }


    public void TroGiup5050(View view) {
        TroGiup50 = false;
        ImageView img = findViewById(R.id.imgTroGiup5050);
        img.setImageResource(R.drawable.loaitrogiup5050);
        img.setEnabled(false);
        Random r = new Random();
        int vtda = cauHoi.getVtDA();
        do {
            vtRandom1 = r.nextInt(4);
        } while (vtRandom1 == vtda);
        do {
            vtRandom2 = r.nextInt(4);
        } while (vtRandom2 == vtRandom1 || vtRandom2 == vtda);
        Log.d("rd1", vtRandom1 + "");
        Log.d("rd2", vtRandom2 + "");
        int vtA = cauHoi.getVtA();
        int vtB = cauHoi.getVtB();
        int vtC = cauHoi.getVtC();
        int vtD = cauHoi.getVtD();
        if (vtA == vtRandom1 || vtA == vtRandom2) {
            PhuongAnA.setVisibility(View.INVISIBLE);
        }
        if (vtB == vtRandom1 || vtB == vtRandom2) {
            PhuongAnB.setVisibility(View.INVISIBLE);
        }
        if (vtC == vtRandom1 || vtC == vtRandom2) {
            PhuongAnC.setVisibility(View.INVISIBLE);
        }
        if (vtD == vtRandom1 || vtD == vtRandom2) {
            PhuongAnD.setVisibility(View.INVISIBLE);
        }

    }

    public void TroGiupDoiCauHoi(View view) {
        DoiCauHoi = false;
        HienThiCauHoi();
        ImageView img = findViewById(R.id.imgTroGiupDoiCauHoi);
        img.setImageResource(R.drawable.loaidoicauhoi);
        img.setEnabled(false);
    }

    public void TroGiupKhanGia(View view) {
        stopDongHo();
        Intent intent = new Intent(this, TroGiupKhanGia.class);

        ImageView img = findViewById(R.id.imgTroGiupKhanGia);
        img.setImageResource(R.drawable.loaitrogiupkhangia);
        img.setEnabled(false);


        intent.putExtra("TroGiup50", TroGiup50);
        if (TroGiup50 == false) {
            intent.putExtra("DaXoa1", vtRandom1);
            intent.putExtra("DaXoa2", vtRandom2);
        }

        startActivity(intent);
    }

    public void TroGiupNguoiThan(View view) {
        stopDongHo();

        ImageView img = findViewById(R.id.imgTroGiupNguoiThan);
        img.setImageResource(R.drawable.loaitrogiupgoidienthoai);
        img.setEnabled(false);
        TroGiupGoi = false;
        Intent intent = new Intent(this, TroGiupNguoiThan.class);
        if (TroGiup50 == false) {
            intent.putExtra("DaXoa1", vtRandom1);
            intent.putExtra("DaXoa2", vtRandom2);
        }
        startActivity(intent);
    }

}