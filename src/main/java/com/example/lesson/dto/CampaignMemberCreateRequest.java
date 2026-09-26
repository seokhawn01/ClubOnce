package com.example.lesson.dto;

import jakarta.validation.constraints.NotNull;

public record CampaignMemberCreateRequest(
        @NotNull Long memberId,
        @NotNull Long feeCampaignId
) {}
