package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_MuaCredit.MuaCredit;

public class Asystask_MuaCredit extends AsyncTask<String, String, String> {
    private Context context;

    public Asystask_MuaCredit(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... value) {
        return APIGoiCredit.getAPIGoiCredit();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            Intent intent = new Intent(context, MuaCredit.class);
            intent.putExtra("DanhSachGoiCredit",s);
            Activity activity = (Activity) context;
            activity.startActivity(intent);
        } else {
            Activity activity = (Activity) context;
            Toast.makeText(activity, "Không tìm thấy chuỗi Json", Toast.LENGTH_SHORT).show();
        }
    }
}
