package com.project.weatherbetting.region.entity;

import com.project.weatherbetting.region.dto.RegionParsingResult;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "regions")
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_code", nullable = false, unique = true)
    private String regionCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String district;

    private String dong;

    @Column(nullable = false)
    private Integer nx;

    @Column(nullable = false)
    private Integer ny;

    // 위도
    @Column(nullable = false)
    private BigDecimal latitude;

    // 경도
    @Column(nullable = false)
    private BigDecimal longitude;

    public Region(RegionParsingResult result) {
        this.regionCode = result.regionCode();
        this.city = result.city();
        this.district = result.district();
        this.dong = result.dong();
        this.nx = result.nx();
        this.ny = result.ny();
        this.latitude = result.latitude();
        this.longitude = result.longitude();
    }

}
