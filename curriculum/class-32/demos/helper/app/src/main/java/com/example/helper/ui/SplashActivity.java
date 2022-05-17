package com.example.helper.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.helper.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //We are using a theme to show the splash screen and not a actual layout
        // therefore setcontentview is not needed in this case
//        setContentView(R.layout.activity_splash);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}