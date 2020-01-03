package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.doanandroid.Class.DongHo;
import com.example.doanandroid.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Random;

public class TroGiupKhanGia extends AppCompatActivity {

    BarChart barChart;
    Button btnTroVe;
    boolean troGiup50;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_tro_giup_khan_gia);
        btnTroVe = findViewById(R.id.btnTroVe);
        btnTroVe.setEnabled(false);
        intent=getIntent();
        troGiup50=intent.getBooleanExtra("TroGiup50",true);
        Log.d("trogiup50",troGiup50+"");
    }

    public void Back(View view) {
        finish();

    }

    public void HienThi(View view) {
        btnTroVe.setEnabled(true);
        Button btnXem = findViewById(R.id.btnXem);
        btnXem.setEnabled(false);
        if(troGiup50) {

            float KhanGiaA;
            float KhanGiaB;
            float KhanGiaC;
            float KhanGiaD;
            int PTConLai = 100;

            Random r = new Random();
            do {
                KhanGiaA = r.nextInt(PTConLai);
            } while (KhanGiaA > 40 || KhanGiaA < 10);
            PTConLai -= KhanGiaA;
            do {
                KhanGiaB = r.nextInt(PTConLai);
            } while (KhanGiaB > 40 || KhanGiaB < 10);
            PTConLai -= KhanGiaB;
            do {
                KhanGiaC = r.nextInt(PTConLai);
            } while (KhanGiaC > 40 || KhanGiaC < 10);
            PTConLai -= KhanGiaC;
            KhanGiaD = PTConLai;
            Log.d("ptA", KhanGiaA + "");
            Log.d("ptB", KhanGiaB + "");
            Log.d("ptC", KhanGiaC + "");
            Log.d("ptD", KhanGiaD + "");
            VeBieuDo(KhanGiaA, KhanGiaB, KhanGiaC, KhanGiaD);
        }
        else
        {
            int daXoa1=intent.getIntExtra("DaXoa1",-1);
            int daXoa2=intent.getIntExtra("DaXoa2",-1);
            float KhanGiaA;
            float KhanGiaB;
            float KhanGiaC;
            float KhanGiaD;
            int PTConLai = 100;
            Random r = new Random();
            boolean daXoa=false;
            if(daXoa1==0||daXoa2==0)
            {
                KhanGiaA=0;
            }
            else
            {
                if(daXoa==true)
                {
                    KhanGiaA=PTConLai;
                }
                else
                {
                    do {
                        KhanGiaA=r.nextInt(PTConLai);
                    }while (KhanGiaA > 80 || KhanGiaA < 20);
                    daXoa=true;
                    PTConLai-=KhanGiaA;
                }
            }
            if(daXoa1==1||daXoa2==1)
            {
                KhanGiaB=0;
            }
            else {
                if(daXoa==true)
                {
                    KhanGiaB=PTConLai;
                }
                else
                {
                    do {
                        KhanGiaB=r.nextInt(PTConLai);
                    }while (KhanGiaB > 80 || KhanGiaB < 20);
                    daXoa=true;
                    PTConLai-=KhanGiaB;
                }
            }
            if(daXoa1==2||daXoa2==2)
            {
                KhanGiaC=0;
            }
            else {
                if(daXoa==true)
                {
                    KhanGiaC=PTConLai;
                }
                else
                {
                    do {
                        KhanGiaC=r.nextInt(PTConLai);
                    }while (KhanGiaC > 80 || KhanGiaC < 20);
                    daXoa=true;
                    PTConLai-=KhanGiaC;
                }
            }
            if(daXoa1==3||daXoa2==3)
            {
                KhanGiaD=0;
            }
            else {
                KhanGiaD=PTConLai;
            }
            Log.d("PTA",KhanGiaA+"");
            Log.d("PTB",KhanGiaB+"");
            Log.d("PTC",KhanGiaC+"");
            Log.d("PTD",KhanGiaD+"");
            VeBieuDo(KhanGiaA,KhanGiaB,KhanGiaC,KhanGiaD);
        }
    }

    public void VeBieuDo(float a, float b, float c, float d) {
        barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> datas = new ArrayList<>();
        datas.add(new BarEntry(0, a));
        datas.add(new BarEntry(1, b));
        datas.add(new BarEntry(2, c));
        datas.add(new BarEntry(3, d));

        BarDataSet barDataset = new BarDataSet(datas, "Khán giả bình chọn");
        barDataset.setBarBorderColor(R.drawable.mau_dung);
        barDataset.setValueTextSize(20f);

        BarData barData = new BarData(barDataset);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        final String[] labels = new String[]{"A", "B", "C", "D"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(labels);
        xAxis.setTextSize(20f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        barChart.setData(barData);
        barChart.setFitBars(true);
        //bo ve luoi
        xAxis.setDrawGridLines(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.setTouchEnabled(false);
        barChart.animateXY(5000, 5000);
        barChart.invalidate();
    }
}
