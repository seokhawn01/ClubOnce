package com.example.lesson.dto;

import java.math.BigDecimal;
// 납부 현황 조회
public record PaymentSummaryResponse(
        Long campaignMemberId,
        String applicantName,
        BigDecimal expectedAmount,
        BigDecimal paidAmount
) {
    public PaymentSummaryResponse {
        paidAmount = (paidAmount != null) ? paidAmount : BigDecimal.ZERO;
    }
}
