package com.example.weather_service.dto;

public class WeatherResponse {
    private String city;
    private String date;
    private double temperature;
    private String conditions;
    public WeatherResponse() {}
    public WeatherResponse(String city, String date, double temperature, String conditions) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        this.conditions = conditions;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public String getConditions() { return conditions; }
    public void setConditions(String conditions) { this.conditions = conditions; }
}
