package com.example.foraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView mBottomNav = findViewById(R.id.bottom_navigation);
        Toolbar myToolBar = findViewById(R.id.top_toolbar);
        mBottomNav.setItemIconTintList(null);
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavController navController = NavHostFragment.findNavController(Objects.requireNonNull(navHostFragment));
        NavigationUI.setupWithNavController(myToolBar, navController);
        NavigationUI.setupWithNavController(mBottomNav, navController);
    }
}