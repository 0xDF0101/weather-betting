package com.project.weatherbetting.betting.entity;

import com.project.weatherbetting.user.entity.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "bettings")
@NoArgsConstructor
public class Betting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stat_id", nullable = false)
    private BettingStat bettingStat;

    @Enumerated(EnumType.STRING)
    @Column(name = "bet_choice", nullable = false)

    private BetChoice betChoice;

    @Column(name = "bet_amount", nullable = false)
    private Long betAmount;

    @Column(nullable = false)
    private BigDecimal odds;

    @Column(name = "commission_rate", nullable = false)
    private BigDecimal commissionRate;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;



}
