package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi.TroChoiMoi;


public class AsyncTask_TroChoiMoi extends AsyncTask<String, String, String> {

    private Context context;

    public AsyncTask_TroChoiMoi(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... value) {
        return APIQuanLiTaiKhoan.getAPIDSLinhVuc();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {

            Intent intent = new Intent(context, TroChoiMoi.class);
            intent.putExtra("DanhSachLinhVuc", s);
            Activity activity = (Activity) context;
            activity.startActivity(intent);
        } else {
//            Toast.makeText(activity,s.toString(),Toast.LENGTH_SHORT).show();
            Activity activity = (Activity) context;
            Toast.makeText(activity, "Không tìm thấy chuỗi Json", Toast.LENGTH_SHORT).show();
        }
    }
}