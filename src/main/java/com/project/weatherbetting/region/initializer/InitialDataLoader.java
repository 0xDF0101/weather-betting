package com.project.weatherbetting.region.initializer;

import com.project.weatherbetting.region.parser.RegionParser;
import com.project.weatherbetting.region.service.DataLoaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements ApplicationRunner {

    private final DataLoaderService dataLoaderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataLoaderService.loadIfEmpty();
    }
}
