package com.example.lesson.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
        name = "payment"
)
@Getter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_member_id") //null로 값이 들어오면 매칭이 안된거
    private CampaignMember campaignMember;

    @Column(length = 20,nullable = false)
    private String payerName;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Instant paidAt; //실제 입금일

    @Column
    private Instant createdAt;

    public Payment(CampaignMember campaignMember, String payerName, BigDecimal amount, Instant paidAt) {
        this.campaignMember = campaignMember;
        this.payerName = payerName;
        this.amount = amount;
        this.paidAt = paidAt;
        this.createdAt = Instant.now();
    }
}
