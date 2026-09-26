package com.example.lesson.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Table(
        name = "fee_campaign"
)
@Getter
@NoArgsConstructor
public class FeeCampaign {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 100)
        private String name;

        @Column(nullable = false, length = 100)
        private String category;

        @Column(nullable = false, precision = 12, scale = 2)
        private BigDecimal defaultAmount;

        private Integer capacity;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, length = 20, name = "status")
        private FeeCampaignStaus feeCampaignStaus;

        @Column(name = "created_at", nullable = false, updatable = false)
        private Instant createdAt;

        @Column(name = "starts_at", nullable = false)
        private Instant startsAt; // 납부 시작일

        @Column(name = "ends_at", nullable = false)
        private Instant endsAt; // 납부 마감일

        public FeeCampaign(
                String name,
                String category,
                BigDecimal defaultAmount,
                Integer capacity,
                FeeCampaignStaus feeCampaignStaus,
                Instant startsAt,
                Instant endsAt
                ) {
            this.name = name;
            this.defaultAmount = defaultAmount;
            this.capacity = capacity;
            this.category = category;
            this.feeCampaignStaus = feeCampaignStaus;
            this.createdAt = Instant.now();
            this.startsAt = startsAt;
            this.endsAt = endsAt;
        }

}
