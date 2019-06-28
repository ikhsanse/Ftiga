package com.example.ftiga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private View view;

    CircleImageView ftProfil;

    TextView edtProfil, nmProfil;

    CardView history, qna, about_us, logout;

    String id_user, nama, email, foto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        id_user = getActivity().getIntent().getExtras().getString("id_user");
        nama = getActivity().getIntent().getExtras().getString("nama");
        email = getActivity().getIntent().getExtras().getString("email");

        nmProfil = (TextView) view.findViewById(R.id.tv_name);
        ftProfil =  (CircleImageView) view.findViewById(R.id.imv_logo);

        edtProfil = (TextView) view.findViewById(R.id.tv_profile);
        edtProfil.setOnClickListener(this);

        qna = (CardView) view.findViewById(R.id.cv_profil2);
        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Qna.class);
                getActivity().startActivity(intent1);
            }
        });

        about_us = (CardView) view.findViewById(R.id.cv_profil3);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), About_Us.class);
                getActivity().startActivity(intent1);
            }
        });

        logout = (CardView) view.findViewById(R.id.cv_profil4);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                System.exit(0);
            }
        });

        if(JsonUtils.isNetworkAvailable(getActivity())){
            new Tampil().execute("http://fff.invicit.com/test/get_profile.php?id_user="+ id_user);
        }else{
            Toast.makeText(getActivity(),"No Network Connection!!!",Toast.LENGTH_SHORT).show();
        }


        return view;
    }

    public class Tampil extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
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
                Toast.makeText(getActivity(), "Tidak Ada data!!!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject JsonUtama = new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JsonObj = jsonArray.getJSONObject(i);

                        nmProfil.setText(JsonObj.getString("nama"));
                        foto = JsonObj.getString("foto");


                        if(foto.equals("")){
                            ftProfil.setImageResource(R.drawable.default_avatar);
                        }
                        else{
                            Picasso
                                    .get()
                                    .load("http://fff.invicit.com/test/MyFiles/profil/"+id_user+".jpg")
                                    .fit()
                                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                                    .networkPolicy(NetworkPolicy.NO_CACHE)
                                    .into(ftProfil);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_profile:{
                Intent intent = new Intent(getActivity(), EditProfil.class);
                intent.putExtra("id_user",id_user);
                intent.putExtra("nama",nama);
                intent.putExtra("email",email);
                getActivity().startActivity(intent);
            }
                break;
            default:
                break;
        }
    }
}
