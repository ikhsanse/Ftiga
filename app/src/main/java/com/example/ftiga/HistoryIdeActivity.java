package com.example.ftiga;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class HistoryIdeActivity extends RecyclerView.Adapter<HistoryIdeActivity.ViewHolder> {

    private ArrayList<String> rvData;


    public HistoryIdeActivity(ArrayList<String> rvData) {
        this.rvData = rvData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView tv_judul_ide;
        public TextView tv_jumlah_donasi;

        public ViewHolder(View v) {
            super(v);
            tv_judul_ide = (TextView) v.findViewById(R.id.tv_judul_ide);
            tv_jumlah_donasi = (TextView) v.findViewById(R.id.tv_total_dana);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history_ide, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut
        final String name = rvData.get(position);
        holder.tv_judul_ide.setText(rvData.get(position));
        holder.tv_jumlah_donasi.setText("Rp" + position);
    }


    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }

}
