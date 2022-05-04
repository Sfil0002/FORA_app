package com.example.foraapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class qr_reader_activity extends AppCompatActivity {

    Button scanBtn, cancelbtn;
    TextView messageText, messageFormat;
    ListView pulledItem;
    ImageView pulledImage;
    private FirebaseDatabase database =FirebaseDatabase.getInstance("https://fora-app-ed8dc-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference FORA_Database = database.getReference("Animal");
    private List<String> animal_list;
    private ArrayAdapter<String> animal_adapter;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_reader);

        //referencing and initializing
        // the button and textviews
        scanBtn = findViewById(R.id.scanBtn);
        cancelbtn = findViewById(R.id.cancelbtn);
        messageText = findViewById(R.id.textContent);



        // adding listener to the button
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we need to create the object
                // of IntentIntegrator class
                // which is the class of QR library
                IntentIntegrator intentIntegrator = new IntentIntegrator(qr_reader_activity.this);
                intentIntegrator.setPrompt("Scan a barcode or QR Code");

                //intentIntegrator.setOrientationLocked(true);
                intentIntegrator.initiateScan();
            }
        });

        //cancel button
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
            else
            {
                // if the intentResult is not null we'll set
                // the content and format of scan message
                messageText.setText(intentResult.getContents());
                //messageFormat.setText(intentResult.getFormatName());
                //The content of the QR is stored in the variable
                // allowing for a query to be formed
                String scannedinfo = intentResult.getContents();
                Query query = FORA_Database.orderByChild("builtQRCode").equalTo(scannedinfo);
                query.addListenerForSingleValueEvent(valueEventListener);
                //The query calls a eventlistener that returns the item from the database


            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            pulledItem = findViewById(R.id.pulledItem);
            animal_list = new ArrayList<>();
            pulledImage = findViewById(R.id.pulledImage);
                for(DataSnapshot pulledAnimal : snapshot.getChildren())
                     {
                        //Load the queried data into the view and initialize variables
                         Animal animal = pulledAnimal.getValue(Animal.class);
                         assert animal != null;
                         animal_list.add(animal.toStringShort());
                         url = animal.toLoadImage();

                     }
                    animal_adapter = new ArrayAdapter<String>(qr_reader_activity.this, android.R.layout.simple_list_item_1,animal_list);
                    //Adapter stores data into the list and then is set
                    //Picasso is used to load the image based of url
                    Picasso.get().load(url).into(pulledImage);
                    pulledItem.setAdapter(animal_adapter);


            }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(qr_reader_activity.this, "Error reading", Toast.LENGTH_SHORT).show();
        }
    };

}