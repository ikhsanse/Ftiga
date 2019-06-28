package com.example.ftiga;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Layout_history_donasi extends AppCompatActivity {

    HistoryDonaturAdapter objAdapter;
    ListView listData;
    List<ItemHistoryDonatur> arrayItemBaru;
    private ItemHistoryDonatur semuaItemObj;
    ArrayList<String> alljudul, alldana;
    String [] arrayjudul, arraydana;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_history_donasi);

        listData = findViewById(R.id.lv_history_donatur);
        progressBar = findViewById(R.id.progbar);
        arrayItemBaru = new ArrayList<ItemHistoryDonatur>();

        alljudul = new ArrayList<>();
        alldana = new ArrayList<>();

        arrayjudul = new String [alljudul.size()];
        arraydana = new String[alldana.size()];

        final int id_user = 1;

        if(JsonUtils.isNetworkAvailable(Layout_history_donasi.this)){
            new Tampil().execute("http://fff.invicit.com/test/get_history_donatur_byid.php?id_user="+id_user);
        }else{
            new AlertDialog.Builder(Layout_history_donasi.this)
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

    }


    public class Tampil extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return JsonUtils.getJSONString(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(null != progressBar){
                progressBar.setVisibility(View.GONE);
            }

            if(s == null || s.length() == 0 ){
                new AlertDialog.Builder(Layout_history_donasi.this)
                        .setTitle("failed")
                        .setMessage("Harap Periksa Koneksi")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                            }
                        }).show();
                progressBar.setVisibility(View.GONE);
            }
            else {
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1 = null;
                    for(int i=0; i<jsonArray.length(); i++){

                        jsonObject1 = jsonArray.getJSONObject(i);
                        ItemHistoryDonatur donatur = new ItemHistoryDonatur();
                        donatur.setJudul(jsonObject1.getString("judul"));
                        donatur.setJumlah_donasi(jsonObject1.getString("jumlah_donasi"));

                        arrayItemBaru.add(donatur);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

                for(int j=0; j<arrayItemBaru.size();j++){
//                    semuaItemObj = arrayItemBaru.get(j);
                    semuaItemObj = arrayItemBaru.get(j);

                    alljudul.add(semuaItemObj.getJudul());
                    arrayjudul = alljudul.toArray(arrayjudul);

                    alldana.add(semuaItemObj.getJumlah_donasi());
                    arraydana = alldana.toArray(arraydana);

                }
                setAllAdapter();

            }

        }

    }

    public void setAllAdapter(){
        objAdapter = new HistoryDonaturAdapter(Layout_history_donasi.this,R.layout.activity_history_donasi,arrayItemBaru);
        listData.setAdapter(objAdapter);
    }



}


