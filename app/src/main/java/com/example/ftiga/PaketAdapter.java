package com.example.ftiga;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PaketAdapter extends ArrayAdapter<ItemPaket> {

    private Activity activity;
    private List<ItemPaket> itembaru;
    private ItemPaket semuaobj;
    private int row;

    public PaketAdapter(Activity act, int resource, List<ItemPaket> arraylist) {
        super(act, resource, arraylist);
        this.activity = act;
        this.row = resource;
        this.itembaru = arraylist;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        PaketAdapter.ViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new PaketAdapter.ViewHolder();
            view.setTag(holder);
        }else {
            holder = (PaketAdapter.ViewHolder) view.getTag();
        }

        if((itembaru == null) || ((position + 1) > itembaru.size()))
            return view;

        semuaobj = itembaru.get(position);

        holder.nama_paket = (TextView) view.findViewById(R.id.item_paket);
        holder.harga = (TextView) view.findViewById(R.id.item_harga);
        holder.feedback = (TextView) view.findViewById(R.id.item_feedback);


//        if(semuaobj.getGambar().toString().equals("")){
//            holder.gmb.setImageResource(R.drawable.default_produk_putih);
//        }else{
//            Picasso
//                    .with(activity)
//                    .load(semuaobj.getGambar().toString())
//                    .fit()
//                    .into(holder.gmb);
//        }

        holder.nama_paket.setText(semuaobj.getPaket().toString());
        holder.harga.setText(semuaobj.getHarga().toString());
        holder.feedback.setText(semuaobj.getFeedback().toString());

        return view;
    }

    public class ViewHolder{
        public TextView nama_paket;
        public TextView harga;
        public TextView feedback;
    }

}
