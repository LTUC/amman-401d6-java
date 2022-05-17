package com.example.helper.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.helper.R;
import com.example.helper.data.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    List<WeatherData> weatherDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initialiseData();

        // get the recycler view object
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // create an adapter
        CustomRecyclerViewAdapter customRecyclerViewAdapter = new CustomRecyclerViewAdapter(
                weatherDataList, position -> {
            Toast.makeText(
                    RecyclerViewActivity.this,
                    "The item clicked => " + weatherDataList.get(position).getCity(), Toast.LENGTH_SHORT).show();

//            startActivity(new Intent(getApplicationContext(), SOMECLASS.class));
        });

        // set adapter on recycler view
        recyclerView.setAdapter(customRecyclerViewAdapter);

        // set other important properties
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set click listener
    }

    private void initialiseData() {
        weatherDataList.add(new WeatherData("Cairo", 45, 50));
        weatherDataList.add(new WeatherData("Amman", 50, 100));
        weatherDataList.add(new WeatherData("Bridgetown", 23, 23));
        weatherDataList.add(new WeatherData("New York", 22, 30));
        weatherDataList.add(new WeatherData("Miami", 21, 15));
        weatherDataList.add(new WeatherData("London", -30, 89));
        weatherDataList.add(new WeatherData("Egypt", 66, 56));
        weatherDataList.add(new WeatherData("Paris", 23, 40));
        weatherDataList.add(new WeatherData("Portugal", 12, 10));
    }
}