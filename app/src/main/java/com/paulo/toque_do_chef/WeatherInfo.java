package com.paulo.toque_do_chef;

public class WeatherInfo {
    private double temperature;
    private int weatherCode;

    public WeatherInfo(double temperature, int weatherCode) {
        this.temperature = temperature;
        this.weatherCode = weatherCode;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getWeatherCode() {
        return weatherCode;
    }
}

