package com.project.weatherbetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    // API 요청을 보내기 위한 RestClient Bean등록
    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
