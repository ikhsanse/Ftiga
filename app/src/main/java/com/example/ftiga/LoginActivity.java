package com.example.ftiga;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    TextView regis;
    Button btnLogin, btnLGoogle;
    String Result,us,ps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText)findViewById(R.id.edtemail);
        edtPassword = (EditText)findViewById(R.id.edtpassword);
        regis = (TextView)findViewById(R.id.regis);

        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us = edtEmail.getText().toString();
                ps = edtPassword.getText().toString();
                if(JsonUtils.isNetworkAvailable(LoginActivity.this)){
                    new Tampil().execute("http://192.168.0.20/test/login.php?email="+us+"&password="+ps);
                }else{
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Failed")
                            .setMessage("Please Check Connection!")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();
                }
            }
        });
//        inisialisasi();
//
        //Button Login
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //Button Register
//        btnLGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent a = new Intent(getApplicationContext(), RegisterActivity.class);
//                startActivity(a);
////            }
////        });

    }

    public class Tampil extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Harap Tunggu...");
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

            if(null == hasil || hasil.length() == 0){
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Failed")
                        .setMessage("Please Check Your Connection!")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                            }
                        }).show();
            }else{
                try {
                    JSONObject JsonUtama =  new JSONObject(hasil);

                    JSONArray res = JsonUtama.getJSONArray("hasil");
                    JSONObject re = null;
                    re = res.getJSONObject(0);

                    final String result = re.getString("result");

                    if (result.equals("true")) {

                        JSONArray jsonArray = JsonUtama.getJSONArray("data");
                        JSONObject JsonObj = null;

                        JsonObj = jsonArray.getJSONObject(0);

//                        final String roll = JsonObj.getString("role");
                        final String id_user = JsonObj.getString("id_user");

//                        if (roll.equals("edtEmail")){
//                            new AlertDialog.Builder(LoginActivity.this)
//                                    .setTitle("Succes")
//                                    .setMessage("Login Berhasil!")
//                                    .setCancelable(false)
//                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            finish();
//                                            Intent a = new Intent(LoginActivity.this, FragmentActivity.class);
//                                            a.putExtra("id",id_user);
//                                            startActivity(a);
//                                        }
//                                    }).show();
//                        }else{
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Succes")
                                    .setMessage("Login Berhasil!")
                                    .setCancelable(false)
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            Intent a = new Intent(LoginActivity.this, FragmentActivity.class);
                                            a.putExtra("id",id_user);
                                            startActivity(a);
                                        }
                                    }).show();
//                        }

                    } else {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Failed")
                                .setMessage("Username Atau Password Salah!")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void inisialisasi(){
      edtEmail = findViewById(R.id.edtemail);
      edtPassword = findViewById(R.id.edtpassword);
      btnLogin = findViewById(R.id.btnlogin);
      btnLGoogle = findViewById(R.id.btnlogin);
    }

}
