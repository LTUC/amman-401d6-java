package com.example.demo26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView secondGreeting = findViewById(R.id.second_greeting);
        TextView secondDescription = findViewById(R.id.second_description);

        // get the intent which was passed
        Intent passedIntent = getIntent();

        // extract the data from the intent
        String greeting = passedIntent.getStringExtra("greeting");
        String description = passedIntent.getStringExtra("description");

        // insert the data into the text views
        secondGreeting.setText(greeting);
        secondDescription.setText(description);
    }
}