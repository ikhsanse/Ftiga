package com.example.ftiga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
    List<ItemDonatur> arrayItembaru;
    DonaturAdapter objAdapter;
    private ItemDonatur semuaItemobj;
    ArrayList<String> allid, alldana, allnama;
    String[] arrayid,  arraydana, arraynama;

    String id_user,id_ide;

//    public FragmentDonaturIde() {
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_donatur_ide, container, false);
        id_user = getActivity().getIntent().getExtras().getString("id");
        id_ide = getActivity().getIntent().getExtras().getString("id_ide");

//        Toolbar tb = (Toolbar) rootView.findViewById(R.id.tb_home);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);
//
        listData = (GridView)rootView.findViewById(R.id.rv_donatur);
        arrayItembaru = new ArrayList<ItemDonatur>();

        allid = new ArrayList<String>();
        alldana = new ArrayList<String>();
        allnama = new ArrayList<String>();

        //menghitung jumlah data
        arrayid = new String[allid.size()];
        arraydana = new String[alldana.size()];
        arraynama = new String[allnama.size()];

        if(JsonUtils.isNetworkAvailable(getActivity())){
            new Tampil().execute("http://192.168.0.20/test/get_donatur_ide.php?id_ide="+id_ide);
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

//        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                semuaItemobj = arrayItembaru.get(position);
//
//                /*String ide = semuaItemobj.getId();
//                String idtoko = semuaItemobj.getIdtoko();*/
//
//                String id_ide = semuaItemobj.getId();
//                String nama = semuaItemobj.getNama_donatur();
//                String dana = semuaItemobj.getJml_donasi();
//
//                Intent a = new Intent(getActivity() ,DeskIdeActivity.class);
//                /*a.putExtra("idtoko",idtoko);
//                a.putExtra("idproduk",ide);*/
//                a.putExtra("id",id_user);
//                a.putExtra("id_ide",id_ide);
//                a.putExtra("nama",nama);
//                a.putExtra("dana",dana);
//                startActivity(a);
//            }
//        });

        return rootView;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Make sure you have this line of code.
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
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
            }else{
                try {
                    JSONObject JsonUtama =  new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for(int i = 0;i < jsonArray.length();i++){

                        JsonObj = jsonArray.getJSONObject(i);

                        ItemDonatur donatur = new ItemDonatur();

                        donatur.setId(JsonObj.getString("id_ide"));
                        donatur.setJml_donasi(JsonObj.getString("jumlah_donasi"));
                        donatur.setNama_donatur(JsonObj.getString("nama"));

                        arrayItembaru.add(donatur);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int j=0;j<arrayItembaru.size();j++){

                    semuaItemobj = arrayItembaru.get(j);

                    allid.add(semuaItemobj.getId());
                    arrayid = allid.toArray(arrayid);

                    alldana.add(semuaItemobj.getJml_donasi());
                    arraydana = alldana.toArray(arraydana);

                    allnama.add(semuaItemobj.getNama_donatur());
                    arraynama = allnama.toArray(arraynama);

                }

                setAllAdapter();

            }
        }
    }

    public void setAllAdapter(){
        objAdapter = new DonaturAdapter(getActivity(),R.layout.item_donatur,arrayItembaru);
        listData.setAdapter(objAdapter);
    }
}
