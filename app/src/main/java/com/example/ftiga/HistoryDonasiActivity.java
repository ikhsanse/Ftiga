package com.example.ftiga;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryDonasiActivity extends RecyclerView.Adapter<HistoryDonasiActivity.ViewHolder> {

    private ArrayList<String> rvData;


    public HistoryDonasiActivity(ArrayList<String> rvData) {
        this.rvData = rvData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView tv_nama_ide;
        public TextView tv_jumlah_donasi;

        public ViewHolder(View v) {
            super(v);
            tv_nama_ide = (TextView) v.findViewById(R.id.tv_nama_ide);
            tv_jumlah_donasi = (TextView) v.findViewById(R.id.tv_jumlah_donasi);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history_donasi, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut
        final String name = rvData.get(position);
        holder.tv_nama_ide.setText(rvData.get(position));
        holder.tv_jumlah_donasi.setText("Rp" + position);
    }


    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }



}
