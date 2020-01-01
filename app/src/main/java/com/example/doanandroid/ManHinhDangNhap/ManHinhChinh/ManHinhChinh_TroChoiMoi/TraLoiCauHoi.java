package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Class.CauHoi;
import com.example.doanandroid.Class.DongHo;
import com.example.doanandroid.R;

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
    private TextView NoiDung, PhuongAnA, PhuongAnB, PhuongAnC, PhuongAnD;
    private String chon = null;
    private String PhuongAnDung = null;
    private int vtht;
    private Random random;
    int n=999;
    int temp=0;
    DongHo dongHo;
    ProgressBar mDongHo;
    public static String kq="start";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_hien_thi_cau_hoi);

        mDongHo=findViewById(R.id.ProBarDongHo);
        NoiDung = findViewById(R.id.txtNoiDung);
        PhuongAnA = findViewById(R.id.txtPhuongAnA);
        PhuongAnB = findViewById(R.id.txtPhuongAnB);
        PhuongAnC = findViewById(R.id.txtPhuongAnC);
        PhuongAnD = findViewById(R.id.txtPhuongAnD);
        dongHo=new DongHo(mDongHo,this);
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
        temp=0;
        kq="start";
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
                PhuongAnDung = cauHoi.getPhuongAnDung();

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


    public void ChonDapAn(View view) {
        switch (view.getId()) {
            case R.id.txtPhuongAnA: {
                final String DapAn = "A";
                PhuongAnA.setBackgroundResource(R.drawable.mau_chon);
                if (KiemTraDapAn(DapAn)) {
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
                    HienThiDapAnDung();
                    finish();
                }
                break;
            }
            case R.id.txtPhuongAnB: {

                final String DapAn = "B";
                PhuongAnB.setBackgroundResource(R.drawable.mau_chon);
                if (KiemTraDapAn(DapAn)) {
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
                    HienThiDapAnDung();
                    finish();
                }
                break;
            }
            case R.id.txtPhuongAnC: {
                final String DapAn = "C";
                PhuongAnC.setBackgroundResource(R.drawable.mau_chon);
                if (KiemTraDapAn(DapAn)) {
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
                    HienThiDapAnDung();
                    finish();
                }
                break;
            }
            case R.id.txtPhuongAnD: {
                final String DapAn = "D";
                PhuongAnD.setBackgroundResource(R.drawable.mau_chon);
                if (KiemTraDapAn(DapAn)) {
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
                    HienThiDapAnDung();
                    finish();
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
    public void pause(View view)
    {
        stopDongHo();
        final Dialog pause=new Dialog(this);
        pause.setContentView(R.layout.pause);
        pause.setCanceledOnTouchOutside(false);
        pause.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        Button btnTiepTuc=pause.findViewById(R.id.btnTiepTuc);
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startDongHo();
                pause.dismiss();
            }
        });
        Button btnThoat=pause.findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        pause.show();
    }
    public void startDongHo()
    {
        dongHo=new DongHo(mDongHo,this);
        dongHo.execute(n,temp);
    }

    public void stopDongHo()
    {
        dongHo.cancel(true);
        temp=DongHo.k;
    }

}
