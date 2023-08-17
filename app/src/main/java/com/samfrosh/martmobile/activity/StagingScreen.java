package com.samfrosh.martmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.samfrosh.martmobile.MainActivity;
import com.samfrosh.martmobile.OnSwipeTouchListener;
import com.samfrosh.martmobile.R;

public class StagingScreen extends AppCompatActivity {
    Button swipeBtn;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staging_screen);

          swipeBtn = findViewById(R.id.swipebtn);

        swipeBtn.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                // Handle left swipe (optional)
            }

            @Override
            public void onSwipeRight() {
                // Handle right swipe (optional)
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}