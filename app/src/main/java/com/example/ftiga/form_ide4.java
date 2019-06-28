package com.example.ftiga;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class form_ide4 extends AppCompatActivity {

    CheckBox cb1;
    Button submit;
    String id_user,judul,tanggal,target,editor, snp1,snp2,snp3,sjml1,sjml2,sjml3,sfbck1,sfbck2,sfbck3, Result, image;
    String id_ide=null;
    /*Boolean cek = false, cek1= false;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ide4);

        //Tombol back
        Toolbar tb = (Toolbar) findViewById(R.id.tb_form_ide3);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //getData
        getData();

        cb1 = findViewById(R.id.persetujuan);
        submit = findViewById(R.id.btnbuat);


//        syarat.setText(image);


//        if(cb1.isChecked()){
//            cek = true;
//        }




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked()){
                    new ide().execute();
                }else {
                    Toast.makeText(form_ide4.this, "Please Agree ", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    //Tombol back
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }



    void getData(){
        judul = getIntent().getExtras().getString("judul");
        tanggal = getIntent().getExtras().getString("tanggal");
        target = getIntent().getExtras().getString("target");
        editor = getIntent().getExtras().getString("editor");
        snp1 = getIntent().getExtras().getString("snp1");
        snp2 = getIntent().getExtras().getString("snp2");
        snp3 = getIntent().getExtras().getString("snp3");
        sfbck1 = getIntent().getExtras().getString("sfbck1");
        sfbck2 = getIntent().getExtras().getString("sfbck2");
        sfbck3 = getIntent().getExtras().getString("sfbck3");
        sjml1 = getIntent().getExtras().getString("sjml1");
        sjml2 = getIntent().getExtras().getString("sjml2");
        sjml3 = getIntent().getExtras().getString("sjml3");
        id_user = getIntent().getExtras().getString("id");
        image = getIntent().getExtras().getString("image");
    }

    public class ide extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            dialog = ProgressDialog.show(form_ide4.this,"","Harap Tunggu...",true);
        }

        @Override
        protected void onPostExecute(Void result) {
            dialog.dismiss();
            hasilResult(Result);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //id_user,judul, tanggal, target, editor,
            Result = getIde(snp1, snp2, snp3, sfbck1, sfbck2, sfbck3, sjml1, sjml2,sjml3, image);
            return null;
        }
    }
    //id_user,judul, tanggal, target, editor,
    public String getIde( String snp1, String snp2, String snp3, String sfbck1, String sfbck2, String sfbck3, String sjml1, String sjml2, String sjml3, String image){
        String result = "";

        HttpClient client = new DefaultHttpClient();

        HttpPost request = new HttpPost("http://fff.invicit.com/test/insert_ide.php");

        try{

            List<NameValuePair> nvp = new ArrayList<NameValuePair>(14);
            nvp.add(new BasicNameValuePair("id_user",id_user));
            nvp.add(new BasicNameValuePair("judul",judul));
            nvp.add(new BasicNameValuePair("tgl_berakhir",tanggal));
            nvp.add(new BasicNameValuePair("dana",target));
            nvp.add(new BasicNameValuePair("deskripsi",editor));
            nvp.add(new BasicNameValuePair("nm0",snp1));
            nvp.add(new BasicNameValuePair("nm1",snp2));
            nvp.add(new BasicNameValuePair("nm2",snp3));
            nvp.add(new BasicNameValuePair("fb0",sfbck1));
            nvp.add(new BasicNameValuePair("fb1",sfbck2));
            nvp.add(new BasicNameValuePair("fb2",sfbck3));
            nvp.add(new BasicNameValuePair("hrg0",sjml1));
            nvp.add(new BasicNameValuePair("hrg1",sjml2));
            nvp.add(new BasicNameValuePair("hrg2",sjml3));
            nvp.add(new BasicNameValuePair("hrg2",sjml3));
            nvp.add(new BasicNameValuePair("ft1",image));


//            HttpResponse response = client.execute(request);
//            result = request(response);

            request.setEntity(new UrlEncodedFormEntity(nvp, HTTP.UTF_8));
            HttpResponse response = client.execute(request);
            result = request(response);

        }catch (Exception ex){
            result =  "Unable to Connect";
        }

        return result;
    };

    public static String request(HttpResponse response){

        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();

        }catch (Exception ex){
            result = "Error";
        }

        return result;
    }

    public void hasilResult(String result) {

        if (result.trim().equalsIgnoreCase("OK")) {
            Toast.makeText(form_ide4.this, "Data berhasil di Insert", Toast.LENGTH_SHORT).show();
        } else if (result.trim().equalsIgnoreCase("Failed")) {
            Toast.makeText(form_ide4.this, "Gagal", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("HasilProses", result);
        }
    }
}
