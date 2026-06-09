package com.project.weatherbetting.betting.entity;

import com.project.weatherbetting.region.entity.Region;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "betting_stats")
@NoArgsConstructor
public class BettingStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Enumerated(EnumType.STRING)
    @Column(name = "bet_type", nullable = false)
    private BetType betType;

    @Column(name = "forecast_time", nullable = false)
    private LocalDateTime forecastTime;

    @Column(name = "over_amount", nullable = false)
    private Long overAmount = 0L;

    @Column(name = "under_amount", nullable = false)
    private Long underAmount = 0L;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "bet_status", nullable = false)
    private BetStatus betStatus = BetStatus.OPEN;


}
