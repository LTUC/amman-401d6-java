package com.example.weatherme.data.remote;

public class CurrentWeather {
    private Double lat;
    private Double lon;
    private Double temp_c;
    private Double windspd_kmh;

    private String wx_desc;
    private String wx_icon;
    private String winddir_compass;

    public CurrentWeather() {
    }

    public CurrentWeather(Double lat, Double lon, Double temp_c, Double windspd_kmh, String wx_desc, String wx_icon, String winddir_compass) {
        this.lat = lat;
        this.lon = lon;
        this.temp_c = temp_c;
        this.windspd_kmh = windspd_kmh;
        this.wx_desc = wx_desc;
        this.wx_icon = wx_icon;
        this.winddir_compass = winddir_compass;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(Double temp_c) {
        this.temp_c = temp_c;
    }

    public Double getWindspd_kmh() {
        return windspd_kmh;
    }

    public void setWindspd_kmh(Double windspd_kmh) {
        this.windspd_kmh = windspd_kmh;
    }

    public String getWx_desc() {
        return wx_desc;
    }

    public void setWx_desc(String wx_desc) {
        this.wx_desc = wx_desc;
    }

    public String getWx_icon() {
        return wx_icon;
    }

    public void setWx_icon(String wx_icon) {
        this.wx_icon = wx_icon;
    }

    public String getWinddir_compass() {
        return winddir_compass;
    }

    public void setWinddir_compass(String winddir_compass) {
        this.winddir_compass = winddir_compass;
    }
}
