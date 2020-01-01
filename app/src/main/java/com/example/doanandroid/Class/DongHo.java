package com.example.doanandroid.Class;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;

import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_TroChoiMoi.TraLoiCauHoi;

import java.lang.ref.WeakReference;

public class DongHo extends AsyncTask<Integer, Integer, String> {
    WeakReference<ProgressBar> mDongHo;
    Context context;
    public static int k;

    public DongHo(ProgressBar progressBar, Context context) {
        this.mDongHo = new WeakReference<>(progressBar);
        this.context = context;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        int n = integers[0];
        int i = integers[1];
        try {
            for (; i <= n; i++) {
                Thread.sleep(30);
                publishProgress(i);
                k = i;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "done";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Activity activity = (Activity) context;
        activity.finish();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        this.mDongHo.get().setProgress(values[0]);

    }
}
