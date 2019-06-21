package com.example.ftiga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PaketActivity extends AppCompatActivity{

    GridView listData;
    ArrayList<ItemPaket> paketList;
    PaketAdapter objAdapter;
    private ItemPaket semuaItemobj;
    ArrayList<String> allid, allpaket, allharga, allfeedback;
    String[] arrayid, arraypaket, arrayharga, arrayfeedback;

    String data;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);

        data = getIntent().getExtras().getString("id");

        listData = findViewById(R.id.paket_rv);
        paketList= new ArrayList<>();

        allid = new ArrayList<String>();
        allpaket = new ArrayList<String>();
        allharga = new ArrayList<String>();
        allfeedback = new ArrayList<String>();

        //menghitung jumlah data
        arrayid = new String[allid.size()];
        arraypaket = new String[allpaket.size()];
        arrayharga = new String[allharga.size()];
        arrayfeedback = new String[allfeedback.size()];

        if(JsonUtils.isNetworkAvailable(PaketActivity.this)){
            new Tampil().execute("http://192.168.0.20/test/get_paket.php");
        }else{
            new AlertDialog.Builder(PaketActivity.this)
                    .setTitle("Failed")
                    .setMessage("Harap Periksa Koneksi!")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Whatever...
                        }
                    }).show();
        }

        /*paketList.add(new ItemPaket(R.drawable.icondesert,"Paket 1","25.000","Sarung"));
        paketList.add(new ItemPaket(R.drawable.icon2,"Paket 2","50.000","Sarung"));
        paketList.add(new ItemPaket(R.drawable.icon4,"Paket 3","75.000","Selimut"));



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridView.LayoutManager rvLayoutManager = layoutManager;
        listData.setLayoutManager(rvLayoutManager);

        PaketAdapter adapter = new PaketAdapter(this, paketList);

        recyclerView.setAdapter(adapter);*/

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                semuaItemobj = paketList.get(position);

                String id_paket = semuaItemobj.getId();
                String id_ide = semuaItemobj.getId_ide();

                Intent a = new Intent(PaketActivity.this ,DonasiActivity.class);
                a.putExtra("id_ide",id_ide);
                a.putExtra("id_paket",id_paket);
                a.putExtra("iduser",data);
                startActivity(a);
            }
        });
        //Tombol back
        Toolbar tb = (Toolbar) findViewById(R.id.tb_plhPaket);
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

    public class Tampil extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String hasil) {
            super.onPostExecute(hasil);

            if(null == hasil || hasil.length() == 0){
                new AlertDialog.Builder(PaketActivity.this)
                        .setTitle("Failed")
                        .setMessage("Harap Periksa Koneksi!")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                            }
                        }).show();
            }else{
                try {
                    JSONObject JsonUtama =  new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for(int i = 0;i < jsonArray.length();i++){

                        JsonObj = jsonArray.getJSONObject(i);

                        ItemPaket paket = new ItemPaket();

                        paket.setId(JsonObj.getString("id_ide"));
                        paket.setPaket(JsonObj.getString("nama"));
                        paket.setHarga(JsonObj.getString("harga"));
                        paket.setFeedback(JsonObj.getString("feedback"));

                        paketList.add(paket);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int j=0;j<paketList.size();j++){

                    semuaItemobj = paketList.get(j);

                    allpaket.add(semuaItemobj.getPaket());
                    arraypaket = allpaket.toArray(arraypaket);

                    allharga.add(semuaItemobj.getHarga());
                    arrayharga = allharga.toArray(arrayharga);

                    allfeedback.add(semuaItemobj.getFeedback());
                    arrayfeedback = allfeedback.toArray(arrayfeedback);

                }

                setAllAdapter();

            }
        }
    }

    public void setAllAdapter(){
        objAdapter = new PaketAdapter(this,R.layout.item_paket,paketList);
        listData.setAdapter(objAdapter);
    }
}
