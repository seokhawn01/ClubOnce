package com.example.lesson.dto;

import java.math.BigDecimal;

public record PaymentSummaryResponse(
        Long campaignMemberId,
        String applicantName,
        BigDecimal expectedAmount,
        BigDecimal paidAmount
) {
}
