package com.example.weatherme.data.remote;

import java.util.List;

public class ForecastWeather {
    List<Day> Days;

    public ForecastWeather() {
    }

    public ForecastWeather(List<Day> days) {
        Days = days;
    }

    public List<Day> getDays() {
        return Days;
    }

    public void setDays(List<Day> days) {
        Days = days;
    }
}


