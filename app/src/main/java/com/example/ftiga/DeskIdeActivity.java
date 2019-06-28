package com.example.ftiga;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeskIdeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    ImageView gbr;

    String id_user, id_ide, judul_ide, nama, dana, deskripsi, foto;

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

        id_user = getIntent().getExtras().getString("id_user");
        id_ide = getIntent().getExtras().getString("id_ide");
        judul_ide = getIntent().getExtras().getString("judul_ide");
        nama = getIntent().getExtras().getString("nama");
        dana = getIntent().getExtras().getString("dana");
        deskripsi = getIntent().getExtras().getString("deskripsi");
        foto = getIntent().getExtras().getString("foto");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        gbr = (ImageView) findViewById(R.id.img_gbrIde);
        /*Picasso
                .get()
                .load("http://fff.invicit.com/test/MyFiles/profil/"+id_user+".jpg")
                .resize(100,100)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(gbr);*/

        if(JsonUtils.isNetworkAvailable(DeskIdeActivity.this)){
            new Tampil().execute("http://fff.invicit.com/test/get_gbr_ide.php?id_ide="+id_ide);
        }else{
            Toast.makeText(DeskIdeActivity.this,"No Network Connection!!!",Toast.LENGTH_SHORT).show();
        }

        //Tombol back
        Toolbar tb = (Toolbar) findViewById(R.id.detail_ide);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public class Tampil extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(DeskIdeActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String hasil) {
            super.onPostExecute(hasil);

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (null == hasil || hasil.length() == 0) {
                Toast.makeText(DeskIdeActivity.this, "Tidak Berhasil!!!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject JsonUtama = new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JsonObj = jsonArray.getJSONObject(i);

                        id_user =JsonObj.getString("id_user");
                        id_ide = JsonObj.getString("id_ide");
                        foto = JsonObj.getString("ft1");



                            Picasso
                                    .get()
                                    .load("http://fff.invicit.com/test/MyFiles/ide/"+id_ide+".jpg")
                                    .fit()
                                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                                    .networkPolicy(NetworkPolicy.NO_CACHE)
                                    .into(gbr);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

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
                data.putString("id_user",id_user);
                data.putString("id_ide",id_ide);
                f.setArguments(data);
            }
            if (position == 1) {
                f = new FragmentPaketIde();

                Bundle data = new Bundle();
                data.putString("id_user",id_user);
                data.putString("id_ide",id_ide);
                f.setArguments(data);
            }
            if (position == 2) {
                f = new FragmentDonaturIde();
                Bundle data = new Bundle();
                data.putString("id_user",id_user);
                data.putString("id_ide",id_ide);
                f.setArguments(data);
            }
            if (position == 3) {
                f = new FragmentUpdateIde();

                Bundle data = new Bundle();
                data.putString("id_user",id_user);
                data.putString("id_ide",id_ide);
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
