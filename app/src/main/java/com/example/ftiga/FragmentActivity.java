package com.example.ftiga;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class FragmentActivity extends AppCompatActivity {

    String id_user,judul_ide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        FragmentActivity.ViewPagerAdapterIde adapter = new FragmentActivity.ViewPagerAdapterIde(getSupportFragmentManager());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        id_user = getIntent().getExtras().getString("id");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();

                            break;
                        case R.id.nav_discover:
                            selectedFragment = new DiscoverFragment();
                            break;
                        case R.id.nav_ide:
                            selectedFragment = new IdeFragment();
                            break;
                        case R.id.nav_chat:
                            selectedFragment = new ChatFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    class ViewPagerAdapterIde extends FragmentStatePagerAdapter {

        public ViewPagerAdapterIde(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            if (position == 0) {
                f = new HomeFragment();

                Bundle data = new Bundle();
                data.putString("id",id_user);
                data.putString("judul_ide",judul_ide);
                f.setArguments(data);
            }
            if (position == 1) {
                f = new DiscoverFragment();
                Bundle data = new Bundle();
                data.putString("id",id_user);
                f.setArguments(data);
            }
            if (position == 2) {
                f = new IdeFragment();
                Bundle data = new Bundle();
                data.putString("id",id_user);
                f.setArguments(data);
            }
            if (position == 3) {
                f = new ChatFragment();
                Bundle data = new Bundle();
                data.putString("id",id_user);
                f.setArguments(data);
            }
            if (position == 4) {
                f = new ProfileFragment();
                Bundle data = new Bundle();
                data.putString("id",id_user);
                f.setArguments(data);
            }
            return f;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String name = null;
            if (position == 0) {
                name = "Home";
            }
            if (position == 1) {
                name = "Discover";
            }
            if (position == 2) {
                name = "Ide";
            }
            if (position == 3) {
                name = "Likes";
            }
            if (position == 4) {
                name = "Profil";
            }
            return name;
        }

        public void AddFragment(HomeFragment homeFragment, String deskripsi) {
        }
        public void AddFragment(DiscoverFragment discoverFragment, String discover) {
        }
        public void AddFragment(IdeFragment ideFragment, String ide) {
        }
        public void AddFragment(ChatFragment chatFragment, String likes) {
        }
        public void AddFragment(ProfileFragment profileFragment, String profil) {
        }
    }
}
