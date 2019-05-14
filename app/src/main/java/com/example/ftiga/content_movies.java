package com.example.ftiga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class content_movies extends AppCompatActivity {
    TextView judul;
    TextView tahun;
    ImageView gambar;
    TextView durasi;
    TextView genre;
    TextView regional;
    TextView sinopsisFilm;
    TextView direktur;
    TextView casting;
    TextView negaraProduksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_movies);

        judul = findViewById(R.id.txtTitleContent);
        tahun = findViewById(R.id.txtYearContent);
        gambar = findViewById(R.id.imgContent);
        durasi = findViewById(R.id.txtDurationContent);
        genre = findViewById(R.id.txtGenreContent);
        regional = findViewById(R.id.txtRegionalContent);
        sinopsisFilm = findViewById(R.id.txtSinopsis);
        direktur = findViewById(R.id.txtDirector);
        casting = findViewById(R.id.txtCast);
        negaraProduksi = findViewById(R.id.txtProductionCountry);

        Intent intent = getIntent();
        judul.setText(intent.getStringExtra("judul"));
        tahun.setText(intent.getStringExtra("tahun"));
        gambar.setImageResource(intent.getIntExtra("gambar",0));
        durasi.setText(intent.getStringExtra("durasi"));
        genre.setText(intent.getStringExtra("genre"));
        regional.setText(intent.getStringExtra("regional"));
        sinopsisFilm.setText(intent.getStringExtra("sinopsisFilm"));
        direktur.setText(intent.getStringExtra("direktur"));
        casting.setText(intent.getStringExtra("casting"));
        negaraProduksi.setText(intent.getStringExtra("negaraProduksi"));

    }
}
