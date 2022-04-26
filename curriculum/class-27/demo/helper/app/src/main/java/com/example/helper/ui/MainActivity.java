package com.example.helper.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.R;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSubmit = findViewById(R.id.btn_submit);
        Spinner citySelector = findViewById(R.id.spinner_city_selector);
        mAddressText = findViewById(R.id.txt_address);
        
        btnSubmit.setOnClickListener(view -> {
            Log.i(TAG, "submit button clicked");
            navigateToWeatherDetails();
        });

        Log.i(TAG, "onCreate: called");
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: called");
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: called : we can see the screen");
        setAddress();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                navigateToSettings();
                return true;
            case R.id.action_copyright:
                Toast.makeText(this, "Copyright 2022", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void navigateToWeatherDetails() {
        Intent weatherDetailsIntent = new Intent(this, WeatherDetailsActivity.class);
        startActivity(weatherDetailsIntent);
    }

    private void navigateToSettings() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void setAddress() {
        // get text out of shared preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // set text on text view address widget
        mAddressText.setText(sharedPreferences.getString(SettingsActivity.ADDRESS, "No Address Set"));
    }
}