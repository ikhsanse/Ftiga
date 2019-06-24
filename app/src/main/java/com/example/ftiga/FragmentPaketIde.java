package com.example.ftiga;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentPaketIde extends Fragment {

    private View view;

    String [] nama = new String[3], harga = new String[3], feedback = new String[3];
    TextView np1,np2,np3,jml1,jml2,jml3,fbck1,fbck2,fbck3;


    public FragmentPaketIde() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_paket_ide, container,false);

        String id_ide = getActivity().getIntent().getExtras().getString("id_ide");

        np1 = view.findViewById(R.id.tv_np1);
        np2 = view.findViewById(R.id.tv_np2);
        np3 = view.findViewById(R.id.tv_np3);
        jml1 = view.findViewById(R.id.tv_jml1);
        jml2 = view.findViewById(R.id.tv_jml2);
        jml3 = view.findViewById(R.id.tv_jml3);
        fbck1 = view.findViewById(R.id.tv_fbck1);
        fbck2 = view.findViewById(R.id.tv_fbck2);
        fbck3 = view.findViewById(R.id.tv_fbck3);



        if(JsonUtils.isNetworkAvailable(getActivity())){
            new Tampil().execute("http://192.168.100.13/test/get_paket.php?id_ide="+id_ide);
        }else{
            new AlertDialog.Builder(getActivity())
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

        return view;

    }

    public class Tampil extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
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
                new AlertDialog.Builder(getActivity())
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

                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JSONObject1 = null;
//                    JSONObject1 = res.getJSONObject(0);

                        for(int a=0; a<jsonArray.length();a++){

                            JSONObject1 = jsonArray.getJSONObject(a);
                            nama[a] = JSONObject1.getString("nama");
                            harga[a] = JSONObject1.getString("harga");
                            feedback[a] = JSONObject1.getString("feedback");


                        }

                        np1.setText(nama[0]);
                        np2.setText(nama[1]);
                        np3.setText(nama[2]);
                        jml1.setText(harga[0]);
                        jml2.setText(harga[1]);
                        jml3.setText(harga[2]);
                        fbck1.setText(feedback[0]);
                        fbck2.setText(feedback[1]);
                        fbck3.setText(feedback[2]);



//                        final String roll = JsonObj.getString("role");
//                        final String id_user = JsonObj.getString("id_user");

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





                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
