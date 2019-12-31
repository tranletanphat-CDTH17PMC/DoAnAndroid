package com.example.doanandroid.Class;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.doanandroid.R;

import java.util.Random;

public class TroGiupKhanGia extends Dialog {
    TextView txtPA_A,txtPA_B,txtPA_C,txtPA_D;
    public TroGiupKhanGia(@NonNull Context context,int viTriDapAnDung) {
        super(context);
        setContentView(R.layout.tro_giup_khan_gia);
        txtPA_A = findViewById(R.id.txtKhanGiaA);
        txtPA_B = findViewById(R.id.txtKhanGiaB);
        txtPA_C = findViewById(R.id.txtKhanGiaC);
        txtPA_D = findViewById(R.id.txtKhanGiaD);
        int arr[]= new int[]{0,0,0,0};
        int max=25;
        for (int i=0;i<arr.length;i++){
            arr[i]=arr[i]+max;
            if(i==viTriDapAnDung-1){
                arr[1]=arr[i]+25;
            }
            max=max+25;
        }
        arr[viTriDapAnDung]= arr[viTriDapAnDung]+25;
        int tong=125;
        int arrPhanTram[]= new int[]{0,0,0,0};
        int soKhanGia=200;
        Random r= new Random();
        for (int i=0;i<soKhanGia;i++) {
            int chon = r.nextInt(tong);
            if (chon >= 0 && chon < arr[0]) {
                arrPhanTram[0]++;
            } else if (chon >= arr[0] && chon < arr[1]) {
                arrPhanTram[1]++;
            } else if (chon >= arr[1] && chon < arr[2]) {
                arrPhanTram[2]++;
            } else {
                arrPhanTram[3]++;
            }
        }

        txtPA_A.setText("A : " + (int) arrPhanTram[0] * 100.0f / soKhanGia + " %");
        txtPA_B.setText("B : " + (int) arrPhanTram[1] * 100.0f / soKhanGia + " %");
        txtPA_C.setText("C : " + (int) arrPhanTram[2] * 100.0f / soKhanGia + " %");
        txtPA_D.setText("D : " + (int) arrPhanTram[3] * 100.0f / soKhanGia + " %");


    }
}
