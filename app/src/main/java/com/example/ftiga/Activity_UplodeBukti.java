package com.example.ftiga;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Activity_UplodeBukti extends AppCompatActivity {

    //Variables
    private Button btnUpload;

    private ImageView addImage;

    private static final int GALLERY_REQUEST = 1;

    String id_user,id_ide,id_paket,jml_donasi,Result;

//    String user, ide, paket, jmlDon;

    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bukti);

        id_user = getIntent().getExtras().getString("id_user");
        id_ide = getIntent().getExtras().getString("id_ide");
        id_paket = getIntent().getExtras().getString("id_paket");

        //Init View
        btnUpload = (Button) findViewById(R.id.btnUploadBkt);

        addImage = (ImageView) findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });




        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DonasiActivity.this, Activity_UplodeBukti.class);
//
//                startActivity(intent);


                jml_donasi = getIntent().getExtras().getString("jumlah_donasi");


                        UploadImageServer();
            }
        });

    }
    public void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(Activity_UplodeBukti.this.getContentResolver(), uri);

                addImage.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void UploadImageServer() {

        ByteArrayOutputStream byteArrayOutputStreamObject ;
        byteArrayOutputStreamObject = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class Edit extends AsyncTask<Void, Void, Void> {
            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(Activity_UplodeBukti.this,"","Harap Tunggu...",true);

            }

            @Override
            protected Void doInBackground(Void... params) {

                Result = getEdit(jml_donasi,ConvertImage,id_user,id_ide,id_paket);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                dialog.dismiss();
                resultEdit(Result);
            }
        }

        new Edit().execute();
    }

    public void resultEdit(String HasilProses){
        if(HasilProses.trim().equalsIgnoreCase("OK")){
            new AlertDialog.Builder(Activity_UplodeBukti.this)
                    .setTitle("Pesan")
                    .setMessage("Donasi berhasil")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Intent a = new Intent(Activity_UplodeBukti.this, PaketActivity.class);
//                            startActivity(a);
                        }
                    }).show();
        }else if(HasilProses.trim().equalsIgnoreCase("Failed")){
            Toast.makeText(Activity_UplodeBukti.this, "Data Gagal Or Failed", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("Hasil Proses", HasilProses);
        }
    }
    public String getEdit(String jumlah_donasi, String addGambar, String id_user, String id_ide, String id_paket){
        String result = "";

        HttpClient client = new DefaultHttpClient();
//        HttpPost request = new HttpPost("http://192.168.0.14/test/insert_users.php");
        HttpPost request = new HttpPost("http://fff.invicit.com/test/insert_donasi.php");

        try{
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(8);
            nvp.add(new BasicNameValuePair("bukti_pembayaran",addGambar));
            nvp.add(new BasicNameValuePair("jumlah_donasi",jumlah_donasi));
            nvp.add(new BasicNameValuePair("id_user",id_user));
            nvp.add(new BasicNameValuePair("id_ide",id_ide));
            nvp.add(new BasicNameValuePair("id_paket",id_paket));
            request.setEntity(new UrlEncodedFormEntity(nvp, HTTP.UTF_8));
            HttpResponse response = client.execute(request);
            result = request(response);

        }catch (Exception ex){
            result = "Unable To connect";
        }

        return result;
    }
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


}
