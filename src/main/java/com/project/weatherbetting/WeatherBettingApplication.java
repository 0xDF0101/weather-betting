package com.project.weatherbetting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WeatherBettingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherBettingApplication.class, args);
    }

}
