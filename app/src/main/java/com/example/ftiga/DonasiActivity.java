package com.example.ftiga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DonasiActivity extends AppCompatActivity {

    TextView jumlah;

    String id_user,id_ide, jml_donasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donasi);

        id_user = getIntent().getExtras().getString("id");
        id_ide = getIntent().getExtras().getString("id_ide");
        jml_donasi = getIntent().getExtras().getString("jumlah_donasi");

        jumlah = findViewById(R.id.tv_jmlhRp);

        jumlah.setText(jml_donasi);

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
