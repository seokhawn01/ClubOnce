package com.example.lesson.domain;

import jakarta.persistence.*;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Table(
        name = "campaign_member",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_applicant_email",
                columnNames = "applicant_email"
        )
)

public class CampaignMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = true)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_capagin_id", nullable = false)
    private FeeCampaign feeCampaign;

    @Column(nullable = false, length = 20)
    private String applicantName;

    @Column(nullable = false, length = 255)
    private String applicantEmail;

    @Column(nullable = false, updatable = false)
    private Instant registerdAt; // Campaign 참가 신청 시간

    @Column(name = "status", nullable = false, length = 20)
    private CampaignMemberStatus campaignMemberStatus;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal expectedAmount;

    public CampaignMember(Member member, String applicantName, String applicantEmail, Instant registerdAt, CampaignMemberStatus campaignMemberStatus, BigDecimal expectedAmount) {
        this.member = member;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.registerdAt = registerdAt;
        this.campaignMemberStatus = campaignMemberStatus;
        this.expectedAmount = expectedAmount;
    }
}
