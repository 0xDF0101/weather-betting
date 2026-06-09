package com.project.weatherbetting.region.entity;

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

}
