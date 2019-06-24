package com.example.ftiga;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DeskIdeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    String id_user, id_ide, judul_ide, nama, dana, deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_ide);

        tabLayout = findViewById(R.id.tablayoutid);
        appBarLayout = findViewById(R.id.appbarId);
        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapterIde adapter = new ViewPagerAdapterIde(getSupportFragmentManager());

        /*adapter.AddFragment(new FragmentDeskripsiIde(), "Deskripsi");
        adapter.AddFragment(new FragmentPaketIde(), "Paket");
        adapter.AddFragment(new FragmentDonaturIde(), "Donatur");
        adapter.AddFragment(new FragmentUpdateIde(), "Update");*/

        id_user = getIntent().getExtras().getString("id");
        id_ide = getIntent().getExtras().getString("id_ide");
        judul_ide = getIntent().getExtras().getString("judul_ide");
        nama = getIntent().getExtras().getString("nama");
        dana = getIntent().getExtras().getString("dana");
        deskripsi = getIntent().getExtras().getString("deskripsi");

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

    class ViewPagerAdapterIde extends FragmentStatePagerAdapter {

        public ViewPagerAdapterIde(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            if (position == 0) {
                f = new FragmentDeskripsiIde();

                Bundle data = new Bundle();
                data.putString("id",id_user);
                f.setArguments(data);
            }
            if (position == 1) {
                f = new FragmentPaketIde();

                Bundle data = new Bundle();
                data.putString("id",id_user);
                f.setArguments(data);
            }
            if (position == 2) {
                f = new FragmentDonaturIde();

                Bundle data = new Bundle();
                f.setArguments(data);
            }
            if (position == 3) {
                f = new FragmentUpdateIde();

                Bundle data = new Bundle();
                data.putString("id",id_user);
                f.setArguments(data);
            }
            return f;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String name = null;
            if (position == 0) {
                name = "Deskripsi";
            }
            if (position == 1) {
                name = "Paket";
            }
            if (position == 2) {
                name = "Donatur";
            }
            if (position == 3) {
                name = "Update";
            }
            return name;
        }

        public void AddFragment(FragmentDeskripsiIde fragmentDeskripsiIde, String deskripsi) {
        }
        public void AddFragment(FragmentPaketIde fragmentPaketIde, String paket) {
        }
        public void AddFragment(FragmentDonaturIde fragmentDonaturIde, String donatur) {
        }
        public void AddFragment(FragmentUpdateIde fragmentUpdateIde, String update) {
        }
    }
}
