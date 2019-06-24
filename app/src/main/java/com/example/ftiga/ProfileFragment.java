package com.example.ftiga;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private View view;

    TextView edtProfil, nmProfil;

    String id_user, nama, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        id_user = getActivity().getIntent().getExtras().getString("id");
        nama = getActivity().getIntent().getExtras().getString("nama");
        email = getActivity().getIntent().getExtras().getString("email");

        nmProfil = view.findViewById(R.id.tv_name);

        nmProfil.setText(nama);

        edtProfil = (TextView) view.findViewById(R.id.tv_profile);
        edtProfil.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_profile:
                Intent intent = new Intent(getActivity(), EditProfil.class);
                intent.putExtra("id",id_user);
                intent.putExtra("nama",nama);
                intent.putExtra("email",email);
                getActivity().startActivity(intent);
//                Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
