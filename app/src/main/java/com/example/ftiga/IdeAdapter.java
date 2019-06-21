package com.example.ftiga;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
public class IdeAdapter extends ArrayAdapter<ItemIde>{

    private Activity activity;
    private List<ItemIde> itembaru;
    private ItemIde semuaobj;
    private int row;
    Context ctx;

    public IdeAdapter(Activity act, int resource, List<ItemIde> arraylist) {
        super(act, resource, arraylist);
        this.activity = act;
        this.row = resource;
        this.itembaru = arraylist;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

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

        holder.judul_ide = (TextView) view.findViewById(R.id.judul_ide);
        holder.dana = (TextView) view.findViewById(R.id.txt_dana);
        holder.nama = (TextView) view.findViewById(R.id.tv_namaOrg);

//        if(semuaobj.getGambar().toString().equals("")){
//            holder.gmb.setImageResource(R.drawable.default_produk_putih);
//        }else{
//            Picasso
//                    .with(activity)
//                    .load(semuaobj.getGambar().toString())
//                    .fit()
//                    .into(holder.gmb);
//        }

        holder.judul_ide.setText(semuaobj.getJudul_ide().toString());
        holder.dana.setText(semuaobj.getDana().toString());
        holder.nama.setText(semuaobj.getNama().toString());

        return view;
    }

    public class ViewHolder{
        public TextView judul_ide;
        public TextView dana;
        public TextView nama;
    }
}
