package com.project.weatherbetting.weather.service;

import com.project.weatherbetting.region.dto.Coordinate;
import com.project.weatherbetting.region.service.RegionService;
import com.project.weatherbetting.weather.dto.ForecastApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForecastService {

    private final RestClient restClient;
    private final RegionService regionService;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.base_url}")
    private String BASE_URL;

    /**
     *
     * regionCode를 입력받아서 예보를 받아오는 서비스
     * 가장 최신의 basetime을 기반으로 가져온다!
     *
     * @param regionCode
     * @return ForecastApiResponse
     */
    public ForecastApiResponse getForecast(String regionCode) {

        Base base = recentBaseTime(LocalDateTime.now());
        String baseDate = base.baseDate;
        String baseTime = base.baseTime;

        // x, y 좌표 가져오기
        Coordinate coordinate = regionService.getCoordinate(regionCode);

        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("serviceKey", apiKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", coordinate.nx())
                .queryParam("ny", coordinate.ny())
                .build(true)
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(ForecastApiResponse.class);
    }

    //        0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300
    private Base recentBaseTime(LocalDateTime now) {

        int hour = Integer.parseInt(now.format(DateTimeFormatter.ofPattern("HH")));
        String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int baseTimeInt = 0;
        if(hour >= 23) {
            baseTimeInt = 23;
        } else if(hour >= 20) {
            baseTimeInt = 20;
        } else if(hour >= 17) {
            baseTimeInt = 17;
        } else if(hour >= 14) {
            baseTimeInt = 14;
        } else if(hour >= 11) {
            baseTimeInt = 11;
        } else if(hour >= 8) {
            baseTimeInt = 8;
        } else if(hour >= 5) {
            baseTimeInt = 5;
        } else if(hour >= 2) {
            baseTimeInt = 2;
        } else {
            // 하루 전날 예보
            baseDate = now.minusDays(1L).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            baseTimeInt = 23;
        }

        String baseTime = String.format("%02d00", baseTimeInt);

        return new Base(baseDate, baseTime);
    }

    private record Base(
        String baseDate,
        String baseTime
    ) {}
}
