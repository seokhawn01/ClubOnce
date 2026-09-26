package com.example.lesson.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

// 입금한걸 등록할 때
public record PaymentCreateRequest(
        @NotNull Long campaignMemberId,
        @NotBlank @Size(max=20) String payerName,
        @NotNull @Positive BigDecimal amount,
        @NotNull Instant paidAt
        ) {
}
