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

public class FragmentDeskripsiIde extends Fragment implements View.OnClickListener {

    private View view;

    Button btn_donasi;

    public FragmentDeskripsiIde() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_deskripsi_ide, container,false);

        btn_donasi = (Button) view.findViewById(R.id.btn_donasiSkrg);
        btn_donasi.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_donasiSkrg:
                Intent intent = new Intent(getActivity(), PaketActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }
}
