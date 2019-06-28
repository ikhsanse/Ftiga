package com.example.ftiga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import static android.app.Activity.RESULT_OK;

public class FragmentUpdateIde extends Fragment {
    private View view;

    EditText judul, deskripsi;

    private  ImageView pilihGambar;

    Button submit;

    Bitmap bitmap;

    private static final int GALLERY_REQUEST = 1;

    String Result, id_user, id_ide;

    String jdllap,desklap;

    public FragmentUpdateIde() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_update_ide, container,false);

        id_user = getActivity().getIntent().getExtras().getString("id_user");


        judul = (EditText) view.findViewById(R.id.updjudul);
        deskripsi = (EditText) view.findViewById(R.id.updDesk);

        submit = (Button) view.findViewById(R.id.btnSubmit);


        pilihGambar = (ImageView) view.findViewById(R.id.pilihGambar);
        pilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jdllap = judul.getText().toString();
                desklap = deskripsi.getText().toString();
                id_ide = getActivity().getIntent().getExtras().getString("id_ide");

                UploadImageServer();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), uri);

                pilihGambar.setImageBitmap(bitmap);

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
                dialog = ProgressDialog.show(getActivity(),"","Harap Tunggu...",true);

            }

            @Override
            protected Void doInBackground(Void... params) {

                Result = getEdit(jdllap,desklap,ConvertImage,id_ide);
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
            Toast.makeText(getActivity(), "Laporan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(EditProfil.this, EditProfil.class));
        }else if(HasilProses.trim().equalsIgnoreCase("Failed")){
            Toast.makeText(getActivity(), "Data Gagal Or Failed", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("Hasil Proses", HasilProses);
        }
    }



    public String getEdit(String judul, String deskripsi, String pilihGambar, String id_ide){
        String result = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("http://fff.invicit.com/test/insert_laporan.php");
        try{
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(8);
            nvp.add(new BasicNameValuePair("judul",judul));
            nvp.add(new BasicNameValuePair("deskripsi",deskripsi));
            nvp.add(new BasicNameValuePair("foto",pilihGambar));
            nvp.add(new BasicNameValuePair("id_ide",id_ide));
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
