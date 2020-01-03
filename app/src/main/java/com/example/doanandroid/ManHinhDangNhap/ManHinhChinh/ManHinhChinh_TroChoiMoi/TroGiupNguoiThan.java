package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.doanandroid.R;

import java.util.Random;

public class TroGiupNguoiThan extends AppCompatActivity {

    boolean troGiup50;
    Intent intent;
    String PhuongAn;
    int vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_giup_nguoi_than);
        intent = getIntent();
        troGiup50 = intent.getBooleanExtra("TroGiup50", true);
        vt = RandomTroGiup();
        LayDA(vt);
    }

    public void TroGiup(View v) {
        switch (v.getId()) {
            case R.id.imgNguoiThan1: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Sang Sama");
                dialog.setMessage("Theo suy nghĩ của mình thì mình chọn câu " + PhuongAn);
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.create();
                dialog.show();
                break;
            }
            case R.id.imgNguoiThan2: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Mèo ngốc nghếch");
                dialog.setMessage("Gì ai biết đâu chắc câu " + PhuongAn);
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.create();
                dialog.show();
                break;
            }
            case R.id.imgNguoiThan3: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Chú thỏ damdang");
                dialog.setMessage("Theo suy nghĩ của tôi thì t chọn câu " + PhuongAn);
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.create();
                dialog.show();
                break;
            }
            case R.id.imgNguoiThan4: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Chuột đáng thương");
                dialog.setMessage("Theo tôi đáp án đúng là  " + PhuongAn);
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.create();
                dialog.show();
                break;
            }

        }
    }

    public int RandomTroGiup() {
        int giaTri;
        Random r = new Random();
        if (troGiup50) {
            giaTri = r.nextInt(4);
            return giaTri;
        } else {
            int daXoa1 = intent.getIntExtra("DaXoa1", -1);
            int daXoa2 = intent.getIntExtra("DaXoa2", -1);
            do {
                giaTri = r.nextInt(4);
            } while (giaTri == daXoa1 || giaTri == daXoa2);
            return giaTri;
        }
    }

    private void LayDA(int vt) {
        if (vt == 0) {
            PhuongAn = "A";
        } else if (vt == 1) {
            PhuongAn = "B";
        } else if (vt == 2) {
            PhuongAn = "C";
        } else if (vt == 3) {
            PhuongAn = "D";
        }
    }
}
