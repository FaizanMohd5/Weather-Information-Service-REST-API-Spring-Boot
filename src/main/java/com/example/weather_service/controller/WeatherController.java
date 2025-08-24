package com.example.weather_service.controller;

import com.example.weather_service.dto.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    @Value("${visualcrossing.api.key}")
    private String apiKey;

    @Value("${visualcrossing.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Cacheable(value = "weather", key="#city")
    @GetMapping("/weather/{city}")
    public WeatherResponse getWeather(@PathVariable String city) throws Exception {
        
    	String url = apiUrl + city + "?unitGroup=metric&key=" + apiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("Invalid city: " + city);
        }

        JsonNode root = objectMapper.readTree(response.getBody());

        if (root.has("error")) {
            throw new IllegalArgumentException("City not found: " + city);
        }

        String resolvedCity = root.get("resolvedAddress").asText();
        JsonNode today = root.get("days").get(0);

        String date = today.get("datetime").asText();
        double temp = today.get("temp").asDouble();
        String conditions = today.get("conditions").asText();

        return new WeatherResponse(resolvedCity, date, temp, conditions);
    }

}
