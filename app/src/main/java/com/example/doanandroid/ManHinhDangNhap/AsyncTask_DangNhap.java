package com.example.doanandroid.ManHinhDangNhap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.doanandroid.API.APINguoiChoi;
import com.example.doanandroid.Class.NguoiChoi;
import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AsyncTask_DangNhap extends AsyncTask<String, String, String> {

    private String sTK;
    private String sMK;
    private ArrayList<NguoiChoi> nguoiChoi;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public AsyncTask_DangNhap(String sTK, String sMK, Context context) {
        this.sTK = sTK;
        this.sMK = sMK;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... value) {
        return APINguoiChoi.getDSAPINguoiChoi();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            int dem = 0;
            if (getThongTinNguoiChoi(s)) {
                for (int i = 0; i < nguoiChoi.size(); i++) {
                    if (sTK.equals(nguoiChoi.get(i).getTenDangNhap()) && sMK.equals(nguoiChoi.get(i).getMatKhau())) {
                        sharedPreferences = context.getSharedPreferences("nguoiChoi", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor = sharedPreferences.edit();
                        editor.putString("id", nguoiChoi.get(i).getID());
                        editor.putString("ten_dang_nhap", nguoiChoi.get(i).getTenDangNhap());
                        editor.putString("credit", nguoiChoi.get(i).getCredit());
                        editor.putString("email", nguoiChoi.get(i).getEmail());
                        editor.putString("diem_cao_nhat", nguoiChoi.get(i).getDiemCaoNhat());
                        editor.putString("hinh_dai_dien", nguoiChoi.get(i).getHinhDaiDien());
                        editor.commit();
                        Intent intent = new Intent(context, ManHinhChinh.class);
                        Activity activity = (Activity) context;
                        activity.startActivity(intent);
                    } else {
                        dem++;
                    }
                }
                if (dem == nguoiChoi.size()) {
                    Toast.makeText(context, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }

            }
        } else {
//            Toast.makeText(activity,s.toString(),Toast.LENGTH_SHORT).show();
            Activity activity = (Activity) context;
            Toast.makeText(activity, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean getThongTinNguoiChoi(String jSonString) {
        try {
            nguoiChoi = new ArrayList<>();
            JSONObject root = new JSONObject(jSonString);
            JSONArray jr = root.getJSONArray("data");
            ;
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = jr.getJSONObject(i);
                NguoiChoi thongTin = new NguoiChoi();
                thongTin.setID(jb.getString("id"));
                thongTin.setTenDangNhap(jb.getString("ten_dang_nhap"));
                thongTin.setMatKhau(jb.getString("mat_khau"));
                thongTin.setEmail(jb.getString("email"));
                thongTin.setHinhDaiDien(jb.getString("hinh_dai_dien"));
                thongTin.setDiemCaoNhat(jb.getString("diem_cao_nhat"));
                thongTin.setCredit(jb.getString("credit"));
                nguoiChoi.add(thongTin);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }


}
