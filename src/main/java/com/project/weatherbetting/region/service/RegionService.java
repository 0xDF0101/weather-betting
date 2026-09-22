package com.project.weatherbetting.region.service;

import com.project.weatherbetting.common.exception.RegionNotFoundException;
import com.project.weatherbetting.region.dto.Coordinate;
import com.project.weatherbetting.region.dto.RegionSearchResponse;
import com.project.weatherbetting.region.entity.Region;
import com.project.weatherbetting.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<RegionSearchResponse> findByDong(String dong) {

        if(!regionRepository.existsByDong(dong)) {
            log.info("해당 지명은 존재하지 않습니다 : {}", dong);
            throw new RegionNotFoundException("Not Found Region : ," + dong);
        }

        List<Region> regionList = regionRepository.findByDong(dong);

        return regionList.stream()
                .map(region -> new RegionSearchResponse(
                        region.getRegionCode(),
                        region.getCity(),
                        region.getDistrict(),
                        region.getDong()))
                .toList();
    }
}
