package com.project.weatherbetting.region.dto;

public record RegionSearchResponse(
        String regionCode,
        String city,
        String district,
        String dong
) {
}
