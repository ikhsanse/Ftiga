package com.example.ftiga;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class FormIde1 extends AppCompatActivity {

    //Date
    private static final String TAG = "FormIde1";

    EditText edtjudul,edttarget;
    Button btnnext, btnChoose;
    String judulide,target,date,id_user;
    Bitmap bitmap;
    String ConvertImage;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //Variables
//    private Button btnChoose;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ide1);

        id_user = getIntent().getExtras().getString("id_user");

        //Inisialisasi
        inisialisasi();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FormIde1.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy" + month + "/" + day + "/" + year);
                date = year + "-" + "0" + month + "-" + day;
                mDisplayDate.setText(date);
            }
        };

        //Init View
//        btnChoose = (Button) findViewById(R.id.btnChoose);


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });





        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), FormIde2.class);
                getText();
                a.putExtra("judul", judulide);
                a.putExtra("target", target);
                a.putExtra("tanggal", date);
                a.putExtra("id", id_user);
                a.putExtra("image", ConvertImage);
                startActivity(a);
            }
        });


        //Tombol back
        Toolbar tb = (Toolbar) findViewById(R.id.tb_form_ide1);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    void getText(){
        judulide = edtjudul.getText().toString();
        target = edttarget.getText().toString();
    }

    void inisialisasi(){
        edtjudul = findViewById(R.id.edtjudul);
        edttarget = findViewById(R.id.edttarget);
        mDisplayDate =  findViewById(R.id.tvDate);
        btnChoose = findViewById(R.id.btnChoose);
        btnnext = findViewById(R.id.btnnext2);
        imageView = findViewById(R.id.imgView);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null)
        {
            filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

                ByteArrayOutputStream byteArrayOutputStreamObject ;
                byteArrayOutputStreamObject = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
                byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

                ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //Tombol back
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
