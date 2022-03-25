package com.example.foraapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class homeFragment extends Fragment {
    private FirebaseDatabase database =FirebaseDatabase.getInstance("https://fora-app-ed8dc-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference FORA_Database = database.getReference("Animal");
    private GridView catalog_animals;
    private List<String> animal_list;
    private ArrayAdapter<String> animal_adapter;
    private Animal animal = new Animal();

    public homeFragment() {
        // Required empty public constructor
    }



    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        animal_list = new ArrayList<>();
        catalog_animals = view.findViewById(R.id.catalog_animals);
        FORA_Database.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot pulledAnimal : snapshot.getChildren())
                {
                    Animal animal = pulledAnimal.getValue(Animal.class);
                    assert animal != null;
                    animal_list.add(animal.toStringShort());
                }
                animal_adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,animal_list);
                catalog_animals.setAdapter(animal_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(view.getContext(), "Error reading from database", Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }
}