package com.example.foraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);



        Toolbar myToolBar = findViewById(R.id.top_toolbar);

        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavController navController = NavHostFragment.findNavController(Objects.requireNonNull(navHostFragment));
        NavigationUI.setupWithNavController(myToolBar, navController);

        myToolBar.setOnMenuItemClickListener(item -> {
            switch(item.getItemId()){
                case R.id.login_Toolbar:
                    openLogin();
                    break;

            }
            return true;
        });

    }
    private void openLogin()
    {
        startActivity(new Intent(UserActivity.this, login.class));
    }
}