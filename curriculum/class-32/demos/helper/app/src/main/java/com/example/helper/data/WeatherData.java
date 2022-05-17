package com.example.helper.data;

public class WeatherData {

    private final String city;
    private final Integer temperature;
    private final Integer windSpeed;

    public WeatherData(String city, Integer temperature, Integer windSpeed) {
        this.city = city;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }
}
