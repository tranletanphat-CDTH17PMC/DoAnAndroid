package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.doanandroid.API.APINguoiChoi;
import com.example.doanandroid.API.LuotChoiAPI;
import com.example.doanandroid.Class.NguoiChoi;
import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.BangXepHang.BangXepHang;
import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_LichSu.LichSuChoi;

public class AsyncTask_XepHang extends AsyncTask<String, String, String> {
    private Context context;

    AsyncTask_XepHang(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... value) {
        return APINguoiChoi.getDSAPINguoiChoi();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(context, BangXepHang.class);
        intent.putExtra("DSNguoiChoi",s);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }
}
