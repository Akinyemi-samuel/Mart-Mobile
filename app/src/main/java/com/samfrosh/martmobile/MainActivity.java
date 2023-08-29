package com.samfrosh.martmobile;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.samfrosh.martmobile.databinding.ActivityMainBinding;
import com.samfrosh.martmobile.fragment.Cart;
import com.samfrosh.martmobile.fragment.Home;
import com.samfrosh.martmobile.fragment.Profile;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new Home());

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home) {
                    replaceFragment(new Home());
                } else if (item.getItemId() == R.id.about) {
                    replaceFragment(new Profile());
                } else if (item.getItemId() == R.id.cart) {
                    replaceFragment(new Cart());
                } else if (item.getItemId() == R.id.profile) {
                    replaceFragment(new Profile());
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();

    }
}