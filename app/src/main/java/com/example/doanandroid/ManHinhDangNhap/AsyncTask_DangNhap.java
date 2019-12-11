package com.example.doanandroid.ManHinhDangNhap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.doanandroid.ManHinhChinh.ManHinhChinh;

public class AsyncTask_DangNhap extends AsyncTask<String, String, String> {

    private String sTK, sMK;

    private Context context;

    public AsyncTask_DangNhap(String sTK, String sMK, Context context) {
        this.sTK = sTK;
        this.sMK = sMK;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... value) {
        return APINguoiChoi.getAPINguoiChoi(sTK,sMK);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s != null)
        {
            Intent intent = new Intent(context, ManHinhChinh.class);
            intent.putExtra("ThongTinNguoiChoi",s);
            Activity activity = (Activity) context;
            activity.startActivity(intent);
        }
        else {
//            Toast.makeText(activity,s.toString(),Toast.LENGTH_SHORT).show();
            Activity activity = (Activity) context;
            Toast.makeText(activity,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
        }
    }
}
