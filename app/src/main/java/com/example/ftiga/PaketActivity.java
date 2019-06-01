package com.example.ftiga;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
public class PaketActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    ArrayList<ItemPaket> paketList;
    private ItemPaket semuaItemobj;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);

        recyclerView = findViewById(R.id.paket_rv);

        paketList= new ArrayList<>();

        paketList.add(new ItemPaket(R.drawable.icondesert,"Paket 1","25.000","Sarung"));
        paketList.add(new ItemPaket(R.drawable.icon2,"Paket 2","50.000","Sarung"));
        paketList.add(new ItemPaket(R.drawable.icon4,"Paket 3","75.000","Selimut"));



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLayoutManager);

        PaketAdapter adapter = new PaketAdapter(this, paketList);

        recyclerView.setAdapter(adapter);

        /*recyclerView.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                semuaItemobj = paketList.get(position);

                *//*String ide = semuaItemobj.getId();
                String idtoko = semuaItemobj.getIdtoko();*//*

                Intent a = new Intent(PaketActivity.this ,DeskIdeActivity.class);
               *//* a.putExtra("idtoko",idtoko);
                a.putExtra("idproduk",ide);
                a.putExtra("iduser",data);*//*
                startActivity(a);
            }
        });*/
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

}
