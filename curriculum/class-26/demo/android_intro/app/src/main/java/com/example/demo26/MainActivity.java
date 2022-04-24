package com.example.demo26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private final View.OnClickListener mClickMeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mGreetingText.setText("Hi Class this is Demo 26");
            mGreetingText.setAllCaps(true);

            // create intent for navigation
            Intent startSecondActivityIntent = new Intent(getApplicationContext(), SecondActivity.class);

            // add data to intent, there are many more you can add
            startSecondActivityIntent.putExtra("greeting", "Welcome to the second screen");
            startSecondActivityIntent.putExtra("description", "It was a short journey to get here");

            // start / navigate to the next activity
            startActivity(startSecondActivityIntent);
        }
    };
    private TextView mGreetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // this is a good place to do initial
        // set up like click listeners etc
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: Called");

        // get access to the objects inflated from activity_main
        Button clickMeButton = findViewById(R.id.click_me);
        mGreetingText = findViewById(R.id.greeting_text);

        clickMeButton.setOnClickListener(mClickMeButtonListener);

        mGreetingText.setOnClickListener(view -> {
            Toast.makeText(this, "The text view was clicked", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: called");
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: called - The App is VISIBLE");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: called");
        super.onDestroy();
    }
}