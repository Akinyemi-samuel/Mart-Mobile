package com.samfrosh.martmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


import com.samfrosh.martmobile.MainActivity;
import com.samfrosh.martmobile.R;
import com.samfrosh.martmobile.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TranslateAnimation animation = new TranslateAnimation(-1000, 0, 0, 0);
        TranslateAnimation animation2 = new TranslateAnimation(1000, 0, 0, 0);
        animation.setDuration(1000); // Animation duration in milliseconds
        animation2.setDuration(1200); // Animation duration in milliseconds
        binding.img.setAnimation(animation);
        binding.txt.setAnimation(animation2);
        // Start the animation
        binding.img.startAnimation(animation);
        binding.txt.startAnimation(animation2);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.img.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.txt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });





        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        }, 3000);
    }
}