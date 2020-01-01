package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.doanandroid.API.LuotChoiAPI;
import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_LichSu.LichSuChoi;

public class AsyncTask_LichSu extends AsyncTask<String, String, String> {
    private Context context;

    AsyncTask_LichSu(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... value) {
        return LuotChoiAPI.getLichSuChoi(value[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(context, LichSuChoi.class);
        intent.putExtra("DSLuotChoi",s);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }
}
