package com.example.ftiga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DonasiActivity extends AppCompatActivity {

    TextView jumlah;
    ImageView image;
    EditText ulasan,jmh;
    Button uplode;
    Toolbar tb;

    String iduser,idproduk,idtoko;

    String Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donasi);

        Button btn_uplode = (Button) findViewById(R.id.btn_upldBukti);
        btn_uplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonasiActivity.this, Activity_UplodeBukti.class);
                startActivity(intent);
            }
        });
    }
}
