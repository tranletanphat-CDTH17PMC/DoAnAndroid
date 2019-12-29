package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_LichSu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Adapter.LichSuChoiAdapter;
import com.example.doanandroid.Class.LuotChoi;
import com.example.doanandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LichSuChoi extends AppCompatActivity {
    private String jSonLuotChoi;
    private ArrayList<LuotChoi> mLuotChoi;
    private RecyclerView mRecyclerView;
    private LichSuChoiAdapter mAdapter;
    private TextView txtLuotChoi, txtSoCau, txtDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_lich_su_choi);
        txtLuotChoi = findViewById(R.id.txtLuotChoi);
        txtSoCau = findViewById(R.id.txtSoCau);
        txtDiem = findViewById(R.id.txtSoDiem);
        //Bắt intent từ activity màn hình chính
        Intent intent = getIntent();
        jSonLuotChoi = intent.getStringExtra("DSLuotChoi");
        //Hiển thị dữ liệu
        if (getDSLuotChoi(jSonLuotChoi)) {
            //Khởi tạo recycler view
            mRecyclerView = findViewById(R.id.rvLichSu);
            //Khởi tạo adapter và thiết lập nó cho recycler view
            mAdapter = new LichSuChoiAdapter(this, mLuotChoi);
            mRecyclerView.setAdapter(mAdapter);
            //Thiết lập giao diện cho recycler view
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Toast.makeText(this, "API not run", Toast.LENGTH_SHORT).show();
        }
    }

    public void Back(View view) {
        this.finish();
    }

    public boolean getDSLuotChoi(String jSonLuotChoi) {
        try {
            mLuotChoi = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(jSonLuotChoi);
            JSONArray jr = jsonObject.getJSONArray("data");
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject object = jr.getJSONObject(i);
                LuotChoi luotChoi = new LuotChoi();
                luotChoi.setID(object.getString("id"));
                luotChoi.setNguoiChoiID(object.getString("nguoi_choi_id"));
                luotChoi.setSoCau(object.getString("so_cau"));
                luotChoi.setDiem(object.getString("diem"));
                mLuotChoi.add(luotChoi);
            }
//            mAdapter.notifyDataSetChanged();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
