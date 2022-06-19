package com.example.weatherme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.transition.Slide;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.example.weatherme.R;
import com.example.weatherme.api.WeatherAPIService;
import com.example.weatherme.data.remote.CurrentWeather;
import com.example.weatherme.data.remote.Day;
import com.example.weatherme.data.remote.ForecastWeather;
import com.example.weatherme.ui.adapters.ForecastListRecyclerViewAdapter;
import com.example.weatherme.ui.details.WeatherDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Day> days = new ArrayList<>();

    Handler handler;
    private ForecastListRecyclerViewAdapter adapter;

    private void getAuthSession(String method) {
        Amplify.Auth.fetchAuthSession(
                result -> Log.i(TAG, "Auth Session => " + method + result.toString()),
                error -> Log.e(TAG, error.toString())
        );
    }

    private void logout() {
        Amplify.Auth.signOut(
                () -> {
                    Log.i(TAG, "Signed out successfully");
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    getAuthSession("logout");
                    finish();
                },
                error -> Log.e(TAG, error.toString())
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: 6/19/22 ANIMATIONS 1
        setupWindowAnimations();

        handler = new Handler(Looper.getMainLooper(), msg -> {
            Toast.makeText(this, "days length => " + days.size(), Toast.LENGTH_SHORT).show();

            adapter.notifyDataSetChanged();
            return true;
        });

        RecyclerView forecastList = findViewById(R.id.forecast_list);
        adapter = new ForecastListRecyclerViewAdapter(days);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        forecastList.setLayoutManager(linearLayoutManager);
        forecastList.setAdapter(adapter);
        forecastList.setHasFixedSize(true);

        CardView favWeather = findViewById(R.id.fav_weather);
        favWeather.setOnClickListener(view -> {
            Intent navigateToWeatherDetails = new Intent(this,
                    WeatherDetailsActivity.class);
            startActivity(navigateToWeatherDetails);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherunlocked.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPIService weatherAPIService = retrofit.create(WeatherAPIService.class);

        Call<CurrentWeather> current = weatherAPIService.current();
        current.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                assert currentWeather != null;
                Log.i(TAG, "onResponse: current weather" + currentWeather.getWx_desc());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to get Current Weather", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: retrofit => " + t.getMessage());
            }
        });

        Call<ForecastWeather> forecast = weatherAPIService.forecast();
        forecast.enqueue(new Callback<ForecastWeather>() {
            @Override
            public void onResponse(Call<ForecastWeather> call, Response<ForecastWeather> response) {
                ForecastWeather forecastWeather = response.body();
                assert forecastWeather != null;
                Log.i(TAG, "onResponse: forecast weather => " + forecastWeather.getDays());
                days.addAll(forecastWeather.getDays()); // using a singleton would allow to do days = forecastWeather.getDays();
                handler.sendMessage(new Message());
            }

            @Override
            public void onFailure(Call<ForecastWeather> call, Throwable t) {

            }
        });

        FloatingActionButton logWeatherFloatingActionButton = findViewById(R.id.fab_log_weather);
        logWeatherFloatingActionButton.setOnClickListener(view -> {
            startActivity(new Intent(this, LogWeatherActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                logout();
                break;
            case R.id.reset:
                // TODO: 5/25/22 Implement reset password
                break;
            default:
        }
        return true;
    }

    /*
    https://androidrepo.com/repo/lgvalle-Material-Animations-android-animations
     */
    // TODO: 6/19/22 ANIMATION 2
    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }
}