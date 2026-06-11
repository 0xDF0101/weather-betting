package com.project.weatherbetting.region.repository;

import com.project.weatherbetting.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DataLoaderRepository extends JpaRepository<Region, Long> {
}
