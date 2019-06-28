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

public class IdeFragment extends Fragment implements View.OnClickListener{

    Button btnNext;

    private View mview;

    String id_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_ide, container, false);
        mview = inflater.inflate(R.layout.fragment_ide,container,false);

        id_user = getActivity().getIntent().getExtras().getString("id_user");

        btnNext = (Button) mview.findViewById(R.id.btnnext1);
        btnNext.setOnClickListener(this);
        return mview;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnnext1:
                Intent intent = new Intent(getActivity(), FormIde1.class);
                intent.putExtra("id_user",id_user);
                getActivity().startActivity(intent);
                break;
                default:
                    break;
        }
    }
}
