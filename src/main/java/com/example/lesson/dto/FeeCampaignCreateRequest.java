package com.example.lesson.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.time.Instant;

public record FeeCampaignCreateRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 100) String category,
        @NotNull @Positive @Digits(integer = 10, fraction = 2) BigDecimal defaultAmount,
        @Positive Integer capacity,
        @NotNull Instant startsAt,
        @NotNull Instant endsAt
) {}
