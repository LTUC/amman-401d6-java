package com.example.weatherme.data.remote;

public class Day {
    private String date;
    private String sunrise_time;
    private String sunset_time;
    private Double temp_max_c;

    public Day() {
    }

    public Day(String date, String sunrise_time, String sunset_time, Double temp_max_c) {
        this.date = date;
        this.sunrise_time = sunrise_time;
        this.sunset_time = sunset_time;
        this.temp_max_c = temp_max_c;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunrise_time() {
        return sunrise_time;
    }

    public void setSunrise_time(String sunrise_time) {
        this.sunrise_time = sunrise_time;
    }

    public String getSunset_time() {
        return sunset_time;
    }

    public void setSunset_time(String sunset_time) {
        this.sunset_time = sunset_time;
    }

    public Double getTemp_max_c() {
        return temp_max_c;
    }

    public void setTemp_max_c(Double temp_max_c) {
        this.temp_max_c = temp_max_c;
    }
}
