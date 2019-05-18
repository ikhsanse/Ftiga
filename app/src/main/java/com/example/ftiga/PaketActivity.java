package com.example.ftiga;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class PaketActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    ArrayList<ModelPaket> paketList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);

        recyclerView = findViewById(R.id.paket_rv);

        paketList= new ArrayList<>();

        paketList.add(new ModelPaket(R.drawable.icondesert,"Paket 1","25.000","Sarung"));
        paketList.add(new ModelPaket(R.drawable.icon2,"Paket 2","50.000","Sarung"));
        paketList.add(new ModelPaket(R.drawable.icon4,"Paket 3","75.000","Selimut"));



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLayoutManager);

        PaketAdapter adapter = new PaketAdapter(this, paketList);

        recyclerView.setAdapter(adapter);
    }


}
