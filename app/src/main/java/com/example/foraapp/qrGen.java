package com.example.foraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;

public class qrGen extends AppCompatActivity {

        EditText editText;
        Button genbutton, saveImage;
        ImageView imageView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.qr_gen);

            editText = findViewById(R.id.edittext);
            genbutton = findViewById(R.id.genbutton);

            imageView = findViewById(R.id.imageview);
            ActivityCompat.requestPermissions(qrGen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1 );
            ActivityCompat.requestPermissions(qrGen.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1 );
            genbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                    try{
                        BitMatrix bitMatrix = multiFormatWriter.encode(editText.getText().toString(), BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }

    }
