package com.example.foraapp;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class homeFragment extends Fragment {
    private FirebaseDatabase database =FirebaseDatabase.getInstance("https://fora-app-ed8dc-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference FORA_Database = database.getReference("Animal");
    private GridView catalog_animals;

    ArrayList dogList=new ArrayList<>();






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


        catalog_animals = view.findViewById(R.id.catalog_animals);

        dogList.clear();
        FORA_Database.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot pulledAnimal : snapshot.getChildren())
                {
                    Animal animal = pulledAnimal.getValue(Animal.class);
                    assert animal != null;
                    dogList.add(new GridViewItem(animal.toStringShort(), animal.toLoadImage()));
                }
                GridViewImageAdaptor gridViewImageAdaptor = new GridViewImageAdaptor(view.getContext(), R.layout.grid_image_view, dogList);
                catalog_animals.setAdapter(gridViewImageAdaptor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(view.getContext(), "Error reading from database", Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }

    public class GridViewImageAdaptor extends ArrayAdapter {
        ArrayList gridViewItems;

        public GridViewImageAdaptor(Context context, int textViewResourceId, ArrayList objects) {
            super(context, textViewResourceId, objects);
            gridViewItems = objects;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = convertView;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_image_view, null);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            textView.setText(((GridViewItem) gridViewItems.get(position)).getDogDetails());
            Picasso.get().load(((GridViewItem) gridViewItems.get(position)).getImageUrl()).into(imageView);

            return view;
        }
    }



    public class GridViewItem {
        String dogDetails;
        String imageUrl;

        public GridViewItem(String dogDetails, String imageUrl)
        {
            this.dogDetails = dogDetails;
            this.imageUrl = imageUrl;
        }

        public String getDogDetails()
        {
            return this.dogDetails;
        }

        public String getImageUrl()
        {
            return this.imageUrl;
        }
    }




}