package com.example.ftiga;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class RegisterActivity extends AppCompatActivity {
    EditText nama,email,password,kpassword;
    Button btnDaftar;
    String uname,mail,pass;
    String Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = (EditText) findViewById(R.id.edtnama);
        email = (EditText)findViewById(R.id.edtemail);
        password = (EditText)findViewById(R.id.edtpassword);


        btnDaftar = (Button)findViewById(R.id.btndaftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = nama.getText().toString();
                mail = email.getText().toString();
                pass = password.getText().toString();


                new daftar().execute();
            }
        });
    }
    public class daftar extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(RegisterActivity.this,"","Harap Tunggu...",true);

        }

        @Override
        protected Void doInBackground(Void... params) {

            Result = getDaftar(uname,mail,pass);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            dialog.dismiss();
            resultDaftar(Result);
        }
    }
    public void resultDaftar(String HasilProses){
        if(HasilProses.trim().equalsIgnoreCase("OK")){
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Pesan")
                    .setMessage("Pendaftaran berhasil")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent a = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(a);
                        }
                    }).show();
        }else if(HasilProses.trim().equalsIgnoreCase("Failed")){
            Toast.makeText(RegisterActivity.this, "Data Gagal Or Failed", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("HasilProses", HasilProses);
        }
    }
    public String getDaftar( String nama, String email, String password){
        String result = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("http://192.168.0.9/test/insert_users.php");
        try{
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(6);
            nvp.add(new BasicNameValuePair("nama",nama));
            nvp.add(new BasicNameValuePair("email",email));
            nvp.add(new BasicNameValuePair("password",password));
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
