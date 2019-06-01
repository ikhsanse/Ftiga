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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SearchView;

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
    ArrayList<String> allid, alljudul, alldesk;
    String[] arrayid, arrayjudul, arraydeskripsi;
    ProgressBar progress;
    EditText by;

    String data;

    int textlength = 0;

    SearchView search;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        progress = (ProgressBar)rootView.findViewById(R.id.menu_drawer_oleh_progbar);

        listData = (GridView)rootView.findViewById(R.id.menu_drawer_oleh_grid);
        arrayItembaru = new ArrayList<ItemIde>();

        allid = new ArrayList<String>();
        alljudul = new ArrayList<String>();
        alldesk = new ArrayList<String>();

        arrayid = new String[allid.size()];
        arrayjudul = new String[alljudul.size()];
        arraydeskripsi = new String[alldesk.size()];

        if(JsonUtils.isNetworkAvailable(getActivity())){
            new DiscoverFragment.Tampil().execute("http://192.168.43.23/test/get_ide.php");
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

        searchdata();

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                semuaItemobj = arrayItembaru.get(position);

                /*String ide = semuaItemobj.getId();
                String idtoko = semuaItemobj.getIdtoko();*/

                Intent a = new Intent(getActivity() ,DeskIdeActivity.class);
               /* a.putExtra("idtoko",idtoko);
                a.putExtra("idproduk",ide);
                a.putExtra("iduser",data);*/
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

    public void searchdata() {
        search = (SearchView) rootView.findViewById(R.id.cariide);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String text) {
                // TODO Auto-generated method stub
                textlength = text.length();
                arrayItembaru.clear();

                String sc = by.getText().toString();

                if (sc.equals("Judul Ide")) {

                    for (int i = 0; i < arrayjudul.length; i++) {
                        if (textlength <= arrayjudul[i].length()) {
                            if (text.toString().equalsIgnoreCase((String) arrayjudul[i].subSequence(0, textlength))) {
                                ItemIde data = new ItemIde();

                                data.setId(arrayid[i]);
                                data.setJudul_ide(arrayjudul[i]);

                                arrayItembaru.add(data);
                            }
                        }
                    }

                    setAllAdapter();
                }

                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                return false;
            }
        });

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
