package com.example.ftiga;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentDeskripsiIde extends Fragment {

    private View view;

    Button btn_donasi;

    TextView jdl, nm, dn, desk;

    String id_user, id_ide, judul_ide, nama, dana, deskripsi;

    public FragmentDeskripsiIde() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_deskripsi_ide, container,false);

        id_user = getActivity().getIntent().getExtras().getString("id_user");
        id_ide = getActivity().getIntent().getExtras().getString("id_ide");
        judul_ide = getActivity().getIntent().getExtras().getString("judul_ide");
        nama = getActivity().getIntent().getExtras().getString("nama");
        dana = getActivity().getIntent().getExtras().getString("dana");
        deskripsi = getActivity().getIntent().getExtras().getString("deskripsi");

        jdl = view.findViewById(R.id.jdl_ide);
        nm = view.findViewById(R.id.nama_user);
        dn = view.findViewById(R.id.jmlh_terkumpul);
        desk = view.findViewById(R.id.deskripsi);

        jdl.setText(judul_ide);
        nm.setText(nama);
        dn.setText(dana);
        desk.setText(deskripsi);

        btn_donasi = (Button) view.findViewById(R.id.btn_donasiSkrg);
        btn_donasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaketActivity.class);
                intent.putExtra("id_user",id_user);
                intent.putExtra("id_ide",id_ide);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

/*    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_donasiSkrg:
                Intent intent = new Intent(getActivity(), PaketActivity.class);
                intent.putExtra("id",id_user);
                intent.putExtra("id_ide",id_ide);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }*/

}
