package com.example.ftiga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3};

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = rootView.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        Toolbar tb = (Toolbar) rootView.findViewById(R.id.tb_home);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);

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
            new Tampil().execute("http://192.168.0.16/test/get_ide.php");
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

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

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
