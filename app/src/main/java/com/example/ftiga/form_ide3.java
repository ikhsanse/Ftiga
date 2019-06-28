package com.example.ftiga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class form_ide3 extends AppCompatActivity {

    EditText np1, np2,np3, jml1, jml2, jml3, fbck1, fbck2, fbck3;
    Button btnNext4;
    String id_user,judul,tanggal,target,editor, snp1,snp2,snp3,sjml1,sjml2,sjml3,sfbck1,sfbck2,sfbck3, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ide3);
        inisialisasi();


        btnNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passingData();
            }
        });

        //Tombol back
        Toolbar tb = (Toolbar) findViewById(R.id.tb_form_ide3);
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

    void inisialisasi(){
        btnNext4 =  findViewById(R.id.btnnext4);
        np1 = findViewById(R.id.np1);
        np2 = findViewById(R.id.np2);
        np3 = findViewById(R.id.np3);
        jml1 = findViewById(R.id.jml1);
        jml2 = findViewById(R.id.jml2);
        jml3 = findViewById(R.id.jml3);
        fbck1 = findViewById(R.id.fbck1);
        fbck2 = findViewById(R.id.fbck2);
        fbck3 = findViewById(R.id.fbck3);
    }

    void getData(){
        judul = getIntent().getExtras().getString("judul");
        tanggal = getIntent().getExtras().getString("tanggal");
        target = getIntent().getExtras().getString("target");
        editor = getIntent().getExtras().getString("editor");
        id_user = getIntent().getExtras().getString("id");
        image = getIntent().getExtras().getString("image");
    }

    void getEdtText(){
        snp1 = np1.getText().toString();
        snp2 = np2.getText().toString();
        snp3 = np3.getText().toString();
        sjml1 = jml1.getText().toString();
        sjml2 = jml2.getText().toString();
        sjml3 = jml3.getText().toString();
        sfbck1 = fbck1.getText().toString();
        sfbck2 = fbck2.getText().toString();
        sfbck3 = fbck3.getText().toString();
    }

    void passingData(){
        getData();
        getEdtText();
        Intent intent = new Intent(form_ide3.this, form_ide4.class);
        intent.putExtra("judul",judul);
        intent.putExtra("tanggal",tanggal);
        intent.putExtra("target",target);
        intent.putExtra("editor",editor);
        intent.putExtra("snp1",snp1);
        intent.putExtra("snp2",snp2);
        intent.putExtra("snp3",snp3);
        intent.putExtra("sjml1",sjml1);
        intent.putExtra("sjml2",sjml2);
        intent.putExtra("sjml3",sjml3);
        intent.putExtra("sfbck1",sfbck1);
        intent.putExtra("sfbck2",sfbck2);
        intent.putExtra("sfbck3",sfbck3);
        intent.putExtra("id",id_user);
        intent.putExtra("image",image);

        startActivity(intent);
    }
}
