package com.example.weatherme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView favWeather = findViewById(R.id.fav_weather);
        favWeather.setOnClickListener(view -> {
            Intent navigateToWeatherDetails = new Intent(this,
                    WeatherDetailsActivity.class);
            startActivity(navigateToWeatherDetails);
        });
    }
}