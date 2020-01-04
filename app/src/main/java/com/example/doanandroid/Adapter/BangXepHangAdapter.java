package com.example.doanandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Class.LuotChoi;
import com.example.doanandroid.Class.NguoiChoi;
import com.example.doanandroid.R;

import java.util.ArrayList;

public class BangXepHangAdapter extends RecyclerView.Adapter<BangXepHangAdapter.BangXepHangViewHolder> {
    private final ArrayList<NguoiChoi> mNguoiChoi;
    private LayoutInflater mInflater;
    private Context context;

    public BangXepHangAdapter(Context context, ArrayList<NguoiChoi> mNguoiChoi) {
        this.mNguoiChoi = mNguoiChoi;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public BangXepHangAdapter.BangXepHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = mInflater.inflate(R.layout.item_bang_xh, parent, false);
        return new BangXepHangViewHolder(ItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BangXepHangAdapter.BangXepHangViewHolder holder, int position) {
        holder.txtHang.setText(String.valueOf(position + 1));
        holder.txtTen.setText(mNguoiChoi.get(position).getTenDangNhap());
        holder.txtSoDiem.setText(mNguoiChoi.get(position).getDiemCaoNhat());
    }


    @Override
    public int getItemCount() {
        return mNguoiChoi.size();
    }

    public class BangXepHangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView txtHang, txtTen, txtSoDiem;
        final BangXepHangAdapter mAdater;

        public BangXepHangViewHolder(@NonNull View itemView, BangXepHangAdapter mAdater) {
            super(itemView);
            this.txtHang = itemView.findViewById(R.id.txtXH);
            txtTen = itemView.findViewById(R.id.txtTen);
            this.txtSoDiem = itemView.findViewById(R.id.txtDiem);
            this.mAdater = mAdater;
        }

        @Override
        public void onClick(View v) {
            NguoiChoi curNguoiChoi = mNguoiChoi.get(getAdapterPosition());
            String NguoiChoiID = curNguoiChoi.getID();
            Toast.makeText(context, "Ahihi", Toast.LENGTH_SHORT).show();
        }

    }
}
