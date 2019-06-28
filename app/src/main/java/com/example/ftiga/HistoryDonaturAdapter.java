package com.example.ftiga;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryDonaturAdapter extends ArrayAdapter<ItemHistoryDonatur> {

    private Activity activity;
    private List<ItemHistoryDonatur> itembaru;
    private ItemHistoryDonatur semuaobj;
    private int row;
    Context ctx;


    public HistoryDonaturAdapter(Activity act, int resource, List<ItemHistoryDonatur> arraylist) {
        super(act, resource, arraylist);
        this.activity = act;
        this.row = resource;
        this.itembaru = arraylist;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View view = convertView;
        ViewHolder holder;

//        untuk cek listview apakah kosong atau nggak
        if(view == null){

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }

        if((itembaru == null) || ((position + 1) > itembaru.size()))
            return view;

        semuaobj = itembaru.get(position);

        holder.judul = view.findViewById(R.id.tvjudul);
        holder.jumlah_donasi = view.findViewById(R.id.tvtotaldana);

        holder.judul.setText(semuaobj.getJudul());
        holder.jumlah_donasi.setText(semuaobj.getJumlah_donasi());

        return view;
    }


    public class ViewHolder{
        public TextView judul;
        public TextView jumlah_donasi;
    }
}
