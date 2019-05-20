package com.example.ftiga;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView text, textPopup;
    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        mview = inflater.inflate(R.layout.fragment_profile, container, false);

//        text = mview.findViewById(R.id.textProfile);
//        textPopup = mview.findViewById(R.id.textpopUp);
//
//        text.setOnClickListener(this);
//        textPopup.setOnClickListener(this);


        return mview;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

//            case R.id.textProfile:
//                Intent i = new Intent(getActivity(), ProfileActivity.class);
//                getActivity().startActivity(i);
//                break;
//
//            case R.id.textpopUp :
//                Toast.makeText(getActivity(), "This is my Toast message!",
//                        Toast.LENGTH_LONG).show();
//                break;
        }
    }
}
