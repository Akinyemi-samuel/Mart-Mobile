package com.samfrosh.martmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.samfrosh.martmobile.MainActivity;
import com.samfrosh.martmobile.R;
import com.samfrosh.martmobile.databinding.ActivityLoginBinding;
import com.samfrosh.martmobile.service.UserService;
import com.samfrosh.martmobile.service.ValidateInputField;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    private final UserService userService = new UserService();
    private final ValidateInputField validateInputField = new ValidateInputField();
    boolean passwordVisible;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= binding.password.getRight() - binding.password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = binding.password.getSelectionEnd();
                        if (passwordVisible) {
                            binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_off, 0);
                            binding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye, 0);
                            binding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        binding.password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        binding.loginBtn.setOnClickListener(n ->{
            final String email = validateInputField.apply(binding.email);
            final String password = validateInputField.apply(binding.password);

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Empty field", Toast.LENGTH_SHORT).show();
            }else{
                proceedLogin();
            }
        });

    }


    public void proceedLogin(){
        userService.UserLogin(binding.email, binding.password, getApplicationContext(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    public void goToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), Registration.class));
    }
}