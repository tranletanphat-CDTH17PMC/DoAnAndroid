package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class AsyncTask_LoadCauHoi extends AsyncTask<String, String, String> {
    private Context context;

    AsyncTask_LoadCauHoi(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... value) {
        return API_TroChoiMoi.getDSCauHoi(value[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(context, TraLoiCauHoi.class);
        intent.putExtra("DanhSachCauHoi",s);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }

}
