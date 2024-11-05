package com.haitomns.arnaventerprise;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.haitomns.arnaventerprise.databinding.ActivityMainBinding;

import java.time.LocalDate; // Import LocalDate

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    // Set your expiration date (example: November 8, 2024)
    private static final LocalDate EXPIRATION_DATE = LocalDate.of(2025, 11, 15);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the current date is past the expiration date
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(EXPIRATION_DATE) || currentDate.isEqual(EXPIRATION_DATE)) {
            showExpirationDialog(); // Show dialog if expired
            return; // Prevent the app from continuing
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_setting, R.id.nav_account)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void showExpirationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Application Expired")
                .setMessage("The application has expired. Please renew it.")
                .setPositiveButton("OK", (dialog, which) -> finish()) // Close the app on OK
                .setCancelable(false)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}