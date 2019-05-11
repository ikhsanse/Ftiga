package com.example.ftiga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin, btnLGoogle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inisialisasi();

        //Button Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        //Button Register
        btnLGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(a);
            }
        });
    }


    private void inisialisasi(){
      edtEmail = findViewById(R.id.edtemail);
      edtPassword = findViewById(R.id.edtpassword);
      btnLogin = findViewById(R.id.btnlogin);
      btnLGoogle = findViewById(R.id.btnlogin);
    }

}
