package com.example.lesson.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "campaign_member",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_campaign_applicant_email",
                columnNames = {"fee_campaign_id", "applicant_email"}
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
    @JoinColumn(name = "fee_campaign_id", nullable = false)
    private FeeCampaign feeCampaign;

    @Column(nullable = false, length = 20)
    private String applicantName;

    @Column(nullable = false, length = 255)
    private String applicantEmail;

    @Column(nullable = false, updatable = false)
    private Instant registerdAt; // Campaign 참가 신청 시간

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CampaignMemberStatus campaignMemberStatus;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal expectedAmount;

    public CampaignMember(Member member, FeeCampaign feeCampaign) {
        this.member = member;
        this.feeCampaign = feeCampaign;
        this.applicantName = member.getName();
        this.applicantEmail = member.getEmail();
        this.registerdAt = Instant.now();
        this.campaignMemberStatus = CampaignMemberStatus.UNPAID;
        this.expectedAmount = feeCampaign.getDefaultAmount(); // 등록 당시 기본 금액을 보관
    }
}
