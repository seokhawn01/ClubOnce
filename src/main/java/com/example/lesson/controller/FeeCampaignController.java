package com.example.lesson.controller;

import com.example.lesson.dto.FeeCampaignCreateRequest;
import com.example.lesson.dto.FeeCampaignCreateResponse;
import com.example.lesson.service.FeeCampaignService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fee-campaigns")
public class FeeCampaignController {
    private final FeeCampaignService feeCampaignService;

    public FeeCampaignController(FeeCampaignService feeCampaignService) {
        this.feeCampaignService = feeCampaignService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeeCampaignCreateResponse create(@Valid @RequestBody FeeCampaignCreateRequest request) {
        return feeCampaignService.create(request);
    }
}
