package com.project.weatherbetting.region.repository;

import com.project.weatherbetting.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {


    Optional<Region> findByRegionCode(String regionCode);
    List<Region> findByDong(String dong);
    Boolean existsByDong(String dong);
}
