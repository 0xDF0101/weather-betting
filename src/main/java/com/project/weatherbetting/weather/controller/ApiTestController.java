package com.project.weatherbetting.weather.controller;

import com.project.weatherbetting.weather.api.ForecastClient;
import com.project.weatherbetting.weather.dto.ForecastApiResponse;
import com.project.weatherbetting.weather.dto.ForecastRequest;
import com.project.weatherbetting.weather.service.ForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ApiTestController {
    private final ForecastClient client;
    private final ForecastService forecastService;

    @PostMapping("/api/v1/forecast")
    public ResponseEntity<ForecastApiResponse> getForecast(@RequestBody ForecastRequest req) {

        if(req == null) {
            // TODO 테스트니까 예외는 대충 하겠음
            throw new IllegalArgumentException("매개변수가 잘못 넘어온듯 ");
        }

        ForecastApiResponse res = client.getForecast(
                req.baseDate(),
                req.baseTime(),
                req.nx(),
                req.ny()
        );

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/api/v1/forecast/{regionCode}")
    public ResponseEntity<ForecastApiResponse> getForecastByRegionCode(@PathVariable String regionCode) {
        ForecastApiResponse res = forecastService.getForecast(regionCode);
        return ResponseEntity.ok().body(res);
    }


}
