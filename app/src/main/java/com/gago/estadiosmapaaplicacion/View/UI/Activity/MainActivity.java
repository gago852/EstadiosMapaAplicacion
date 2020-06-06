package com.gago.estadiosmapaaplicacion.View.UI.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.gago.estadiosmapaaplicacion.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNav();
    }

    private void configNav() {
        BottomNavigationView navigationView = findViewById(R.id.btMenu);

        NavController navController = Navigation.findNavController(this, R.id.fragContent);

        NavigationUI.setupWithNavController(navigationView, navController);
    }
}
