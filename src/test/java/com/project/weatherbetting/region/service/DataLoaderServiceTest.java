package com.project.weatherbetting.region.service;

import com.project.weatherbetting.region.dto.RegionParsingResult;
import com.project.weatherbetting.region.parser.RegionParser;
import com.project.weatherbetting.region.repository.DataLoaderRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataLoaderServiceTest {

    @Mock
    DataLoaderRepository dataLoaderRepository;
    @Mock
    RegionParser parser;
    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    DataLoaderService dataLoaderService;

    List<RegionParsingResult> results = new ArrayList<>();
    @BeforeEach
    void setUp() {
        results.add(new RegionParsingResult("00000001", "광주광역시", "동구", "동명동", 123, 123, new BigDecimal("123.123"), new BigDecimal("321.321")));
        results.add(new RegionParsingResult("00000002", "서울특별시", "은평구", "동명동", 12, 12, new BigDecimal("13.123"), new BigDecimal("21.321")));
        results.add(new RegionParsingResult("00000003", "남원시", "수지면", "호곡리", 12, 21, new BigDecimal("13.13"), new BigDecimal("31.21")));
    }

    @Test
    @DisplayName("DB가 비어있는 경우 : 정상적인 파싱")
    void loadIfEmpty_empty() throws FileNotFoundException {

        when(dataLoaderRepository.count()).thenReturn(0L);
        when(parser.parse(any(File.class))).thenReturn(results);

        dataLoaderService.loadIfEmpty();

        verify(parser, times(1)).parse(any(File.class));
    }

    @Test
    @DisplayName("DB가 비어있지 않은 경우 : 파싱X")
    void loadIfEmpty_not_empty() throws FileNotFoundException {

        when(dataLoaderRepository.count()).thenReturn(1L);

        dataLoaderService.loadIfEmpty();

        verify(parser, never()).parse(any(File.class));
    }

}
