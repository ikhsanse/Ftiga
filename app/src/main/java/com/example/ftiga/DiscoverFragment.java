package com.example.ftiga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment implements View.OnClickListener {

    GridView listData;
    List<ItemIde> arrayItembaru;
    IdeAdapter objAdapter;
    private ItemIde semuaItemobj;
    ArrayList<String> allid, alljudul, alldana, allnama;
    String[] arrayid, arrayjudul,  arraydana, arraynama;
    ProgressBar progress;

    String data, id_user;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        id_user = getActivity().getIntent().getExtras().getString("id");

        progress = (ProgressBar)rootView.findViewById(R.id.menu_drawer_oleh_progbar);

        listData = (GridView)rootView.findViewById(R.id.menu_drawer_oleh_grid);
        arrayItembaru = new ArrayList<ItemIde>();

        allid = new ArrayList<String>();
        alljudul = new ArrayList<String>();
        alldana = new ArrayList<String>();
        allnama = new ArrayList<String>();

        arrayid = new String[allid.size()];
        arrayjudul = new String[alljudul.size()];
        arraydana = new String[alldana.size()];
        arraynama = new String[allnama.size()];

        if(JsonUtils.isNetworkAvailable(getActivity())){
            new Tampil().execute("http://192.168.0.20/test/get_ide.php");
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

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                semuaItemobj = arrayItembaru.get(position);

                /*String ide = semuaItemobj.getId();
                String idtoko = semuaItemobj.getIdtoko();*/

                String id_ide = semuaItemobj.getId();
                String judul_ide = semuaItemobj.getJudul_ide();
                String nama = semuaItemobj.getNama();
                String dana = semuaItemobj.getDana();
                String deskripsi = semuaItemobj.getDeskripsi();

                Intent a = new Intent(getActivity() ,DeskIdeActivity.class);
                /*a.putExtra("idtoko",idtoko);
                a.putExtra("idproduk",ide);*/
                a.putExtra("id",id_user);
                a.putExtra("id_ide",id_ide);
                a.putExtra("judul_ide",judul_ide);
                a.putExtra("nama",nama);
                a.putExtra("dana",dana);
                a.putExtra("deskripsi",deskripsi);
                startActivity(a);
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_drawer_oleh_grid:
                Intent intent = new Intent(getActivity(), DeskIdeActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
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



            if (null != progress) {
                progress.setVisibility(View.GONE);
            }

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
                progress.setVisibility(View.GONE);
            }else{
                try {
                    JSONObject JsonUtama =  new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for(int i = 0;i < jsonArray.length();i++){

                        JsonObj = jsonArray.getJSONObject(i);

                        ItemIde ide = new ItemIde();

                        ide.setId(JsonObj.getString("id_ide"));
                        ide.setJudul_ide(JsonObj.getString("judul"));
                        ide.setDana(JsonObj.getString("dana"));
                        ide.setNama(JsonObj.getString("nama"));
                        ide.setDeskripsi(JsonObj.getString("deskripsi"));

                        arrayItembaru.add(ide);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int j=0;j<arrayItembaru.size();j++){

                    semuaItemobj = arrayItembaru.get(j);

                    allid.add(semuaItemobj.getId());
                    arrayid = allid.toArray(arrayid);

                    alljudul.add(semuaItemobj.getJudul_ide());
                    arrayjudul = alljudul.toArray(arrayjudul);

                    alldana.add(semuaItemobj.getDana());
                    arraydana = alldana.toArray(arraydana);

                    allnama.add(semuaItemobj.getNama());
                    arraynama = allnama.toArray(arraynama);
                }

                setAllAdapter();

            }
        }
    }

    public void setAllAdapter(){
        objAdapter = new IdeAdapter(getActivity(),R.layout.item_ide,arrayItembaru);
        listData.setAdapter(objAdapter);
    }
}
