package com.example.ftiga;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DonaturAdapter extends ArrayAdapter<ItemDonatur> {

    private Activity activity;
    private List<ItemDonatur> itembaru;
    private ItemDonatur semuaobj;
    private int row;
    Context ctx;
    public DonaturAdapter(Activity act, int resource, List<ItemDonatur> arraylist) {
        super(act, resource, arraylist);
        this.activity = act;
        this.row = resource;
        this.itembaru = arraylist;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        DonaturAdapter.ViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new DonaturAdapter.ViewHolder();
            view.setTag(holder);
        }else {
            holder = (DonaturAdapter.ViewHolder) view.getTag();
        }

        if((itembaru == null) || ((position + 1) > itembaru.size()))
            return view;

        semuaobj = itembaru.get(position);

        holder.nama_donatur = (TextView) view.findViewById(R.id.tv_namaDonatur);
        holder.jml_donasi = (TextView) view.findViewById(R.id.tv_jmlDonasi);


//        if(semuaobj.getGambar().toString().equals("")){
//            holder.gmb.setImageResource(R.drawable.default_produk_putih);
//        }else{
//            Picasso
//                    .with(activity)
//                    .load(semuaobj.getGambar().toString())
//                    .fit()
//                    .into(holder.gmb);
//        }

        holder.nama_donatur.setText(semuaobj.getNama_donatur().toString());
        holder.jml_donasi.setText(semuaobj.getJml_donasi().toString());

        return view;
    }

    public class ViewHolder{
        public TextView nama_donatur;
        public TextView jml_donasi;
    }
}
