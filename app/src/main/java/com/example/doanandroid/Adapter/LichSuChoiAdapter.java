package com.example.doanandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Class.LuotChoi;
import com.example.doanandroid.ManHinhDangNhap.ManHinhChinh.ManHinhChinh_LichSu.LichSuChoi;
import com.example.doanandroid.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LichSuChoiAdapter extends RecyclerView.Adapter<LichSuChoiAdapter.LichSuChoiViewHolder> {
    private final ArrayList<LuotChoi> mLuotChoi;
    private LayoutInflater mInflater;
    private Context context;
    public LichSuChoiAdapter(Context context, ArrayList<LuotChoi> mLuotChoi) {
        this.mLuotChoi = mLuotChoi;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public LichSuChoiAdapter.LichSuChoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = mInflater.inflate(R.layout.item_lichsuchoi, parent, false);
        return new LichSuChoiViewHolder(ItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuChoiAdapter.LichSuChoiViewHolder holder, int position) {

        holder.txtLuotChoi.setText("Lượt chơi:" + String.valueOf(position + 1));
        holder.txtSoCau.setText("Số câu: " + mLuotChoi.get(position).getSoCau());
        holder.txtSoDiem.setText("Tổng điểm: " + mLuotChoi.get(position).getDiem());
    }


    @Override
    public int getItemCount() {
        return mLuotChoi.size();
    }

    public class LichSuChoiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView txtLuotChoi, txtSoCau, txtSoDiem;
        final LichSuChoiAdapter mAdater;
        public final Button btnChiTiet;

        public LichSuChoiViewHolder(@NonNull View itemView, LichSuChoiAdapter mAdater) {
            super(itemView);
            this.txtLuotChoi = itemView.findViewById(R.id.txtLuotChoi);
            this.txtSoCau = itemView.findViewById(R.id.txtSoCau);
            this.txtSoDiem = itemView.findViewById(R.id.txtSoDiem);
            this.mAdater = mAdater;
            this.btnChiTiet = itemView.findViewById(R.id.btnChiTiet);
            btnChiTiet.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            LuotChoi currentLuotChoi = mLuotChoi.get(getAdapterPosition());
            Toast.makeText(context, "Ahihi", Toast.LENGTH_SHORT).show();
        }
    }
}
