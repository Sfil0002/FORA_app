package com.example.foraapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addFragment extends Fragment
{
    private String animal_name, file_number, breed, sex, photo_url, description;
    private int approx_age;
    private EditText name_ET, file_number_ET, breed_ET, sex_ET, photo_url_ET, description_ET;
    private EditText age_ET;
    private FirebaseDatabase database =FirebaseDatabase.getInstance("https://fora-app-ed8dc-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference FORA_Database = database.getReference("Animal");
    private Animal animal = new Animal();


    public addFragment()
    {
        // Required empty public constructor
    }




    public static addFragment newInstance(String param1, String param2)
    {
        addFragment fragment = new addFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add, container, false);
        Button addbutton = view.findViewById(R.id.addAnimal_button);
        addbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name_ET = view.findViewById(R.id.name_ET);
                file_number_ET = view.findViewById(R.id.filenumber_ET);
                breed_ET = view.findViewById(R.id.breed_ET);
                sex_ET = view.findViewById(R.id.sex_ET);
                age_ET = view.findViewById(R.id.age_ET);
                photo_url_ET = view.findViewById(R.id.photoUrl_ET);
                description_ET = view.findViewById(R.id.des_ET);

                animal_name = name_ET.getText().toString();
                file_number = file_number_ET.getText().toString();
                breed = breed_ET.getText().toString();
                sex = sex_ET.getText().toString();
                try
                {
                    approx_age= Integer.parseInt(age_ET.getText().toString());
                }
                catch(NumberFormatException e)
                    {
                        approx_age =0;
                    }
                photo_url = photo_url_ET.getText().toString();
                description = description_ET.getText().toString();

                if(!TextUtils.isEmpty(animal_name) && !TextUtils.isDigitsOnly(animal_name) && !TextUtils.isEmpty(file_number)
                        && !TextUtils.isEmpty(breed) && !TextUtils.isDigitsOnly(breed)&& !TextUtils.isEmpty(sex) && !TextUtils.isDigitsOnly(sex) && approx_age !=0)
                {
                    animal.setAnimal_name(animal_name);
                    animal.setFile_number(file_number);
                    animal.setBreed(breed);
                    animal.setSex(sex);
                    animal.setApprox_age(approx_age);
                    if(TextUtils.isEmpty(photo_url))
                    {
                        photo_url = "Insert image URL for dog";
                    }
                    animal.setPhoto_url(photo_url);
                    animal.setDescription(description);
                    animal.setBuiltQRCode(animal.qrCodeGen());
                    //FORA_Database.push().setValue(animal);
                    FORA_Database.child(animal_name+file_number).setValue(animal);
                    Toast.makeText(view.getContext(), "Animal added!", Toast.LENGTH_SHORT).show();
                    name_ET.getText().clear();
                    file_number_ET.getText().clear();
                    breed_ET.getText().clear();
                    sex_ET.getText().clear();
                    age_ET.getText().clear();
                    photo_url_ET.getText().clear();
                    description_ET.getText().clear();
                }

                else
                {
                    Toast.makeText(view.getContext(), "Please enter values with * or correct values.", Toast.LENGTH_LONG).show();
                    name_ET.getText().clear();
                    file_number_ET.getText().clear();
                    breed_ET.getText().clear();
                    sex_ET.getText().clear();
                    age_ET.getText().clear();
                    photo_url_ET.getText().clear();
                    description_ET.getText().clear();
                }

            }
        });

        return view;
    }
}