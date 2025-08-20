package com.example.weather_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    // Inject values from application.properties
    @Value("${visualcrossing.api.key}")
    private String apiKey;

    @Value("${visualcrossing.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/weather/{city}")
    public String getWeather(@PathVariable String city) {
        String url = apiUrl + city + "?unitGroup=metric&key=" + apiKey;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody(); // return raw JSON for now
    }
}
