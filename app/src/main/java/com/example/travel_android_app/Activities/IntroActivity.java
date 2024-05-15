package com.example.travel_android_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_android_app.R;

public class IntroActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Using a Handler to delay the transition to the main activity
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // Create an Intent that will start the main activity
                Intent mainIntent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish(); // Close the intro activity to prevent returning to it with back button
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}