package com.samfrosh.martmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.samfrosh.martmobile.R;
import com.samfrosh.martmobile.databinding.ActivityRegistrationBinding;
import com.samfrosh.martmobile.dto.Role;
import com.samfrosh.martmobile.service.RegistrationService;
import com.samfrosh.martmobile.service.ValidateInputField;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {

    ValidateInputField validateInputField = new ValidateInputField();
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerBtn.setOnClickListener(n ->{
            final String fname = validateInputField.apply(binding.fname);
            final String lname = validateInputField.apply(binding.fname);
            final String email = validateInputField.apply(binding.fname);
            final String password = validateInputField.apply(binding.fname);

            if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Empty field", Toast.LENGTH_SHORT).show();
            }else{
                proceedRegistration();
            }

        });

    }

    private void proceedRegistration() {
        final String fullname = validateInputField.apply(binding.fname)+ " " + validateInputField.apply(binding.lname);
        final String email = validateInputField.apply(binding.email);
        final String password = validateInputField.apply(binding.password);

        System.out.println(email);
        System.out.println(password);

        JSONObject registrationData = new JSONObject();
        try {
            registrationData.put("fullName", fullname);
            registrationData.put("email", email);
            registrationData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RegistrationService registrationService = new RegistrationService();
        registrationService.RegisterUser(getApplicationContext(), registrationData);
    }

    public void goToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}