package com.example.ftiga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EditProfil extends AppCompatActivity {

    EditText nm_Profil, alamat, eemail, no_tlp, no_rek, no_ktp;

    Button simpan;
    private ImageButton addGambar;

    Bitmap bitmap;

    private static final int GALLERY_REQUEST = 1;

    String id_user, nama, email;

    String foto;
    String namus,almus,emus,tlpus,rekus,ktpus, Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        id_user = getIntent().getExtras().getString("id");
        nama = getIntent().getExtras().getString("nama");
        email = getIntent().getExtras().getString("email");

        nm_Profil = (EditText) findViewById(R.id.edit_nama);
        alamat = (EditText) findViewById(R.id.edit_alamat);
        eemail = (EditText) findViewById(R.id.editemail);
        no_tlp = (EditText) findViewById(R.id.editnotlp);
        no_rek = (EditText) findViewById(R.id.edit_norek);
        no_ktp = (EditText) findViewById(R.id.edit_noktp);

        simpan = (Button) findViewById(R.id.btnSimpanProfil);

        addGambar = (ImageButton) findViewById(R.id.addGambar);

        addGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namus = nm_Profil.getText().toString();
                almus = alamat.getText().toString();
                emus = eemail.getText().toString();
                tlpus = no_tlp.getText().toString();
                rekus = no_rek.getText().toString();
                ktpus = no_ktp.getText().toString();

                UploadImageServer();
            }
        });

        if(JsonUtils.isNetworkAvailable(EditProfil.this)){
            new Tampil().execute("http://192.168.100.13/test/get_edit_profil.php?id_user="+ id_user);
        }else{
            Toast.makeText(EditProfil.this,"No Network Connection!!!",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    public class Tampil extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(EditProfil.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String hasil) {
            super.onPostExecute(hasil);

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (null == hasil || hasil.length() == 0) {
                Toast.makeText(EditProfil.this, "Tidak Ada data!!!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject JsonUtama = new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JsonObj = jsonArray.getJSONObject(i);

                        nm_Profil.setText(JsonObj.getString("nama"));
                        alamat.setText(JsonObj.getString("alamat"));
                        eemail.setText(JsonObj.getString("email"));
                        no_tlp.setText(JsonObj.getString("no_tlp"));
                        no_rek.setText(JsonObj.getString("no_rek"));
                        no_ktp.setText(JsonObj.getString("no_ktp"));
                        foto = JsonObj.getString("foto");

                        Picasso
                                .get()
                                .load(foto)
                                .fit()
                                .into(addGambar);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                addGambar.setImageBitmap(bitmap);

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
                dialog = ProgressDialog.show(EditProfil.this,"","Harap Tunggu...",true);

            }

            @Override
            protected Void doInBackground(Void... params) {

                Result = getEdit(id_user,namus,almus,emus,ConvertImage,tlpus,rekus,ktpus);
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
            Toast.makeText(EditProfil.this, "Profil berhasil diubah", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(EditProfil.this, EditProfil.class));
        }else if(HasilProses.trim().equalsIgnoreCase("Failed")){
            Toast.makeText(EditProfil.this, "Data Gagal Or Failed", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("HasilProses", HasilProses);
        }
    }



    public String getEdit(String id_user,String nm_profil, String alamat, String eemail, String addGambar, String no_tlp, String no_rek, String no_ktp){
        String result = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("http://192.168.100.13/test/update_profil.php");
        try{
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(8);
            nvp.add(new BasicNameValuePair("id_user",id_user));
            nvp.add(new BasicNameValuePair("nama",nm_profil));
            nvp.add(new BasicNameValuePair("alamat",alamat));
            nvp.add(new BasicNameValuePair("email",eemail));
            nvp.add(new BasicNameValuePair("foto",addGambar));
            nvp.add(new BasicNameValuePair("no_tlp",no_tlp));
            nvp.add(new BasicNameValuePair("no_rek",no_rek));
            nvp.add(new BasicNameValuePair("no_ktp",no_ktp));
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