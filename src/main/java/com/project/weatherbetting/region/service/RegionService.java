package com.project.weatherbetting.region.service;

import com.project.weatherbetting.common.exception.RegionNotFoundException;
import com.project.weatherbetting.region.dto.Coordinate;
import com.project.weatherbetting.region.entity.Region;
import com.project.weatherbetting.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public Coordinate getCoordinate(String regionCode) {
        Region region = regionRepository.findByRegionCode(regionCode).orElseThrow(() -> new RegionNotFoundException("Region Not Found : " + regionCode));
        return new Coordinate(region.getNx(), region.getNy());
    }

    // TODO 이름으로 지역 검색하는 용도
    public List<Region> findByDong(String dong) {

        return null;
    }
}
