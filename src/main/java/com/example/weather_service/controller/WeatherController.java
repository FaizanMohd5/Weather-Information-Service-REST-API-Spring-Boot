package com.example.weather_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Marks this as a REST API controller
public class WeatherController {

    // Simple GET API: /weather/{city}
    @GetMapping("/weather/{city}")
    public String getWeather(@PathVariable String city) {
        return "Weather info for " + city;
    }
}
