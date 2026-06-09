package com.project.weatherbetting.betting.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "betting_results")
@NoArgsConstructor
public class BettingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // 이거 굳이 있어야 하나? 1:1인데?
    @JoinColumn(name = "betting_id", nullable = false)
    private Betting betting;

    @Enumerated(EnumType.STRING)
    private Outcome outcome;

    @Column(name = "final_odds")
    private BigDecimal finalOdds;

    @Column(name = "payout_amount")
    private Long payoutAmount;

    @Column(name = "actual_value")
    private BigDecimal actualValue;

    @Column(name = "settled_at")
    private LocalDateTime settledAt;
}
