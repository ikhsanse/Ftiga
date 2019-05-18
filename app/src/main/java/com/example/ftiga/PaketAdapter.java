package com.example.ftiga;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PaketAdapter extends RecyclerView.Adapter<PaketAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelPaket> mlist;
    PaketAdapter(Context context, ArrayList<ModelPaket> list){
        mContext = context;
        mlist = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_paket,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelPaket paket_item = mlist.get(i);

        ImageView icon = viewHolder.item_icon;
        TextView paket,harga,feedback;

        paket = viewHolder.item_paket;
        harga = viewHolder.item_harga;
        feedback = viewHolder.item_feedback;

        icon.setImageResource(paket_item.getIcon());

        paket.setText(paket_item.getPaket());
        harga.setText(paket_item.getHarga());
        feedback.setText(paket_item.getFeedback());


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_icon;
        TextView item_paket,item_harga,item_feedback;
        Button pilih;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_icon = itemView.findViewById(R.id.item_icon);
            item_paket = itemView.findViewById(R.id.item_paket);
            item_harga = itemView.findViewById(R.id.item_harga);
            item_feedback = itemView.findViewById(R.id.item_feedback);
        }
    }

}
