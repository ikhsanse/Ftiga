package com.example.ftiga;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tabLayout = findViewById(R.id.tablayoutid);
        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerHistory adapter = new ViewPagerHistory(getSupportFragmentManager());

        adapter.AddFragment(new FragmentHistoryIde(), "Ide");
        adapter.AddFragment(new FragmentHistoryDonasi(), "Donasi");
    }
}
