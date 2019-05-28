package com.example.ftiga;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        mview = inflater.inflate(R.layout.fragment_profile, container, false);
        tabLayout = mview.findViewById(R.id.tablayoutid);
        viewPager = mview.findViewById(R.id.viewpager_id);

        ViewPagerHistory adapter = new ViewPagerHistory(getFragmentManager());

        adapter.AddFragment(new FragmentHistoryIde(), "Ide");
        adapter.AddFragment(new FragmentHistoryDonasi(), "Donasi");

//        text = mview.findViewById(R.id.textProfile);
//        textPopup = mview.findViewById(R.id.textpopUp);
//
//        text.setOnClickListener(this);
//        textPopup.setOnClickListener(this);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

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
