package com.project.weatherbetting.weather.dto;

public record ForecastRequest(
    String baseDate,
    String baseTime,
    int nx,
    int ny
) {
}
