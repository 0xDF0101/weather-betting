package com.project.weatherbetting.region.controller;

import com.project.weatherbetting.region.dto.RegionSearchResponse;
import com.project.weatherbetting.region.entity.Region;
import com.project.weatherbetting.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/regions")
public class RegionController {
    private final RegionService regionService;

    @GetMapping("/search")
    public ResponseEntity<List<RegionSearchResponse>> getSearchResult(@RequestParam String dong) {

        List<RegionSearchResponse> res = regionService.findByDong(dong);

        return ResponseEntity.ok().body(res);
    }
}
