package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Class.NguoiChoi;
import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_QuanLiTaiKhoan.QuanLiTaiKhoan;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManHinhChinh extends AppCompatActivity {
    TextView txtTenTaiKhoan, txtCredit;
    ArrayList<NguoiChoi> nguoiChoi;
    String thongTinNguoiChoi;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String ten, credit, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_chinh);
        txtTenTaiKhoan = findViewById(R.id.txtUserName);
        txtCredit = findViewById(R.id.txtCredit);

        sharedPreferences = getSharedPreferences("nguoiChoi", MODE_PRIVATE);
        ten = sharedPreferences.getString("ten_dang_nhap", "");
        credit = sharedPreferences.getString("credit", "");
        id = sharedPreferences.getString("id", "");
        txtTenTaiKhoan.setText(ten);
        txtCredit.setText(credit);
//        if (getThongTinNguoiChoi(thongTinNguoiChoi)) {
//            HienThi();
//        } else {
//            Toast.makeText(this, thongTinNguoiChoi, Toast.LENGTH_LONG).show();
//            txtTenTaiKhoan.setText("API not run");
//            txtCredit.setVisibility(View.INVISIBLE);
//        }
    }

    public void HienThi() {
        txtTenTaiKhoan.setText(nguoiChoi.get(0).getTenDangNhap());
        txtCredit.setText(nguoiChoi.get(0).getCredit());
    }

//    private Boolean getThongTinNguoiChoi(String jSonString) {
//        try {
//            nguoiChoi = new ArrayList<>();
//            JSONObject root = new JSONObject(jSonString);
//            JSONArray jr = root.getJSONArray("data");;
//            int num = jr.length();
//            for (int i = 0; i < num; i++) {
//                JSONObject jb = jr.getJSONObject(i);
//                NguoiChoi thongTin = new NguoiChoi();
//                thongTin.setID(jb.getString("id"));
//                thongTin.setTenDangNhap(jb.getString("ten_dang_nhap"));
//                thongTin.setMatKhau(jb.getString("mat_khau"));
//                thongTin.setEmail(jb.getString("email"));
//                thongTin.setHinhDaiDien(jb.getString("hinh_dai_dien"));
//                thongTin.setDiemCaoNhat(jb.getString("diem_cao_nhat"));
//                thongTin.setCredit(jb.getString("credit"));
//                nguoiChoi.add(thongTin);
//            }
//            return true;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public void ManHinhChinh_Click(View view) {
        switch (view.getId()) {
            case R.id.btnQLTK: {
                Intent intent = new Intent(this, QuanLiTaiKhoan.class);
                startActivity(intent);
                break;
            }
            case R.id.btnTroChoiMoi: {
                new AsyncTask_TroChoiMoi(this).execute();
                break;
            }
            case R.id.btnMuaCredit: {
                new Asystask_MuaCredit(this).execute();
                break;
            }
            case R.id.btnLichSuChoi: {
                new AsyncTask_LichSu(this).execute(id);
                break;
            }
            case R.id.btnBangXepHang: {
                new AsyncTask_XepHang(this).execute();
                break;
            }

        }
    }
}
