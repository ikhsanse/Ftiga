package com.example.ftiga;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChatFragment extends Fragment {

    String id_user;
    TextView by;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_chat, container, false);

        id_user = getActivity().getIntent().getExtras().getString("id");

        by = (TextView) rootView.findViewById(R.id.txt_nyoba);

        by.setText(id_user);

        return rootView;
    }
}
