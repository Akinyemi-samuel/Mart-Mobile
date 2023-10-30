package com.samfrosh.martmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.samfrosh.martmobile.R;
import com.samfrosh.martmobile.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void goToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), Registration.class));
    }
}