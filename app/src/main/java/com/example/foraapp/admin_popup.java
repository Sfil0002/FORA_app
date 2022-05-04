package com.example.foraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class admin_popup extends AppCompatActivity {

    private FirebaseDatabase database =FirebaseDatabase.getInstance("https://fora-app-ed8dc-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference FORA_Database = database.getReference("Animal");






    private ImageView animal_photo;
    private FloatingActionButton edit_fab;
    private FloatingActionButton share_fab;
    private FloatingActionButton delete_fab;
    private Button close_popup;
    private View animalInfo;
    private TextView t1;

    private String animal_name;


    // private ListView clickedAnimalData;
    private List<String> animalData;
    ArrayList<Animal> dataShown;
    private ArrayAdapter<String> data_adapter;
    private Animal animal = new Animal();



    private recordFragment records = new recordFragment();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_popup);


        edit_fab = findViewById(R.id.edit_fab);
        share_fab = findViewById(R.id.share_fab);

        close_popup = findViewById(R.id.close_popup);

        t1 = findViewById(R.id.textView);
        t1.setText(getIntent().getExtras().getString("name"));

        recordFragment records = new recordFragment();














        // Edit button to open Firebase link so that admin can access, monitor, & edit database records
        edit_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToUrl("https://console.firebase.google.com/u/0/project/fora-app-ed8dc/database/fora-app-ed8dc-default-rtdb/data");

            }
        });





        share_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_popup.this, qrGen.class));
            }
        });



        close_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin_popup.super.finish();
            }
        });
    }

    private void goToUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}