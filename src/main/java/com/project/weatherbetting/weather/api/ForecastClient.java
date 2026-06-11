package com.project.weatherbetting.weather.api;

import com.project.weatherbetting.weather.dto.ForecastApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class ForecastClient {

    private final RestClient restClient;

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";

    public ForecastApiResponse getForecast(String baseDate, String baseTime, int nx, int ny) {
        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("serviceKey", apiKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(ForecastApiResponse.class);
    }
}
