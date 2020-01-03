package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_tro_giup_khan_gia);
        btnTroVe = findViewById(R.id.btnTroVe);
        btnTroVe.setEnabled(false);
    }

    public void Back(View view) {
        finish();
    }

    public void HienThi(View view) {
        btnTroVe.setEnabled(true);
        Button btnXem = findViewById(R.id.btnXem);
        btnXem.setEnabled(false);
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
