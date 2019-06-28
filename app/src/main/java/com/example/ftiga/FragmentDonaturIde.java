package com.example.ftiga;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentDonaturIde extends Fragment {

    GridView listData;
    List<ItemDonatur> arrayItemBaru;
    DonaturAdapter objAdapter;
    private ItemDonatur semuaItemObj;
    ArrayList<String> allnama, alldana;
    String [] arraynama, arraydana;

    String id_ide;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_donatur_ide, container, false);

        listData = rootView.findViewById(R.id.gv_donatur);

        String id_ide = getActivity().getIntent().getExtras().getString("id_ide");

        arrayItemBaru = new ArrayList<ItemDonatur>();

        allnama = new ArrayList<String>();
        alldana = new ArrayList<String>();

        arraynama = new String[allnama.size()];
        arraydana = new  String[alldana.size()];

        if(JsonUtils.isNetworkAvailable(getActivity())){
            new Tampil().execute("http://fff.invicit.com/test/get_donatur_ide.php?id_ide="+id_ide);
        }else{
            new AlertDialog.Builder(getActivity())
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

        return rootView;
    }

    public class Tampil extends AsyncTask<String, Void, String>{

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Harap Tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            return JsonUtils.getJSONString(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if(s == null || s.length() == 0){
                new AlertDialog.Builder(getActivity())
                        .setTitle("Failed")
                        .setMessage("Harap Periksa Koneksi!")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                            }
                        }).show();

            }else {
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray JsonArray = jsonObject.getJSONArray("datas");
                    JSONObject jsonObject1 = null;

                    for (int i = 0; i < JsonArray.length();i++){

                        jsonObject1 = JsonArray.getJSONObject(i);
                        ItemDonatur donatur = new ItemDonatur();

                        donatur.setNama_donatur(jsonObject1.getString("nama"));
                        donatur.setJml_donasi(jsonObject1.getString("jumlah_donasi"));

                        arrayItemBaru.add(donatur);
                    }

                } catch (JSONException e){
                    e.printStackTrace();
                }
                for (int j = 0;j < arrayItemBaru.size();j++){

                    semuaItemObj = arrayItemBaru.get(j);

                    allnama.add(semuaItemObj.getNama_donatur());
                    arraynama = allnama.toArray(arraynama);

                    alldana.add(semuaItemObj.getJml_donasi());
                    arraydana = alldana.toArray(arraydana);
                }

                setAllAdapter();
            }
        }
    }

    public void setAllAdapter(){
        objAdapter = new DonaturAdapter(getActivity(),R.layout.item_donatur,arrayItemBaru);
        listData.setAdapter(objAdapter);
    }

}
