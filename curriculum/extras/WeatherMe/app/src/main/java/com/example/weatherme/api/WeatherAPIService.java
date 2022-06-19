package com.example.weatherme.api;

import com.example.weatherme.data.remote.CurrentWeather;
import com.example.weatherme.data.remote.ForecastWeather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPIService {
    @GET("api/current/51.50,-0.12?app_id=8c2d4c21&app_key=3e5bf9ecc6d39e156536f594d65b631b")
    Call<CurrentWeather> current();


    @GET("api/forecast/51.50,-0.12?app_id=8c2d4c21&app_key=3e5bf9ecc6d39e156536f594d65b631b")
    Call<ForecastWeather> forecast();
}
