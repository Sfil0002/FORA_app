package com.example.foraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    private EditText et_username, et_password;
    private String username, password;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://fora-app-ed8dc-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference FORA_Database = database.getReference("Credentials");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button login_button = findViewById(R.id.login_button);
        Button cancel_button = findViewById(R.id.cancel_button);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent to open to admin screen
                Intent openAdmin = new Intent(getApplicationContext(), MainActivity.class);

                et_username = findViewById(R.id.et_username);
                et_password = findViewById(R.id.et_password);

                username = et_username.getText().toString();
                password = et_password.getText().toString();


                if ((username.toString().isEmpty() && password.toString().isEmpty()))
                {
                    Toast.makeText(view.getContext(), "Please fill in the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   FORA_Database.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           String pass = (String) snapshot.child("Password").getValue();
                           String user = (String) snapshot.child("Username").getValue();

                           if (!username.equals(user) && !password.equals(pass)) {
                               Toast.makeText(login.this, "Password or username incorrect", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               startActivity(openAdmin);
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {
                           Toast.makeText(view.getContext(), "Error reading from database", Toast.LENGTH_LONG).show();
                       }
                   });
                }
            }
                    });



                }


            }
