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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mAddressText;

    private String[] mCities = new String[]{"Ammna", "Irbid", "Cairo", "Dubai", "New York", "Bridgetown"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSubmit = findViewById(R.id.btn_submit);
        Spinner citySelector = findViewById(R.id.spinner_city_selector);

        // create adapter
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
//                this,
//                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
//                mCities
//        );

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.cities,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        // this is optional
//        spinnerAdapter.setDropDownViewResource("Pass in a resource file");

        // set adapter
        citySelector.setAdapter(spinnerAdapter);
        // this could also be setOnItemClickListener
        citySelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(
                        MainActivity.this,
                        "The item selected is => " + mCities[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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