package com.example.goflight;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DURATION = 5000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity after the splash screen duration
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish(); // Close the splash screen activity
            }
        }, SPLASH_SCREEN_DURATION);
    }
}
