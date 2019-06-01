package com.example.ftiga;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DeskIdeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_ide);

        tabLayout = findViewById(R.id.tablayoutid);
        appBarLayout = findViewById(R.id.appbarId);
        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapterIde adapter = new ViewPagerAdapterIde(getSupportFragmentManager());

        adapter.AddFragment(new FragmentDeskripsiIde(), "Deskripsi");
        adapter.AddFragment(new FragmentPaketIde(), "Paket");
        adapter.AddFragment(new FragmentDonaturIde(), "Donatur");
        adapter.AddFragment(new FragmentUpdateIde(), "Update");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //Tombol back
        Toolbar tb = (Toolbar) findViewById(R.id.detail_ide);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    //Tombol back
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
