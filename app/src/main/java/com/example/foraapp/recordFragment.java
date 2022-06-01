package com.example.foraapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class recordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private FirebaseDatabase database =FirebaseDatabase.getInstance("https://fora-app-ed8dc-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference FORA_Database = database.getReference("Animal");
    private ListView record_scroll;
    private List<String> animal_list;
    private ArrayAdapter<String> animal_adapter;
    private Animal animal = new Animal();
    SearchView searchView;

    // TODO: Rename and change types of parameters

    public recordFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static recordFragment newInstance(String param1, String param2) {
        recordFragment fragment = new recordFragment();
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
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        searchView = view.findViewById(R.id.search_bar);

        animal_list = new ArrayList<>();
        record_scroll = view.findViewById(R.id.record_scroll);
        FORA_Database.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot pulledAnimal : snapshot.getChildren())
                {
                    Animal animal = pulledAnimal.getValue(Animal.class);
                    assert animal != null;
                    animal_list.add(animal.toString());
                }
                animal_adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,animal_list);
                record_scroll.setAdapter(animal_adapter);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        recordFragment.this.animal_adapter.getFilter().filter(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        recordFragment.this.animal_adapter.getFilter().filter(s);
                        return false;
                    }
                });

                // Listener for list view of animals
                record_scroll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                        String str = record_scroll.getAdapter().getItem(i).toString();
                        Intent intent = new Intent(view.getContext(), admin_popup.class);
                        intent.putExtra("name", str);
                        startActivity(intent);
                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(view.getContext(), "Error reading from database", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}