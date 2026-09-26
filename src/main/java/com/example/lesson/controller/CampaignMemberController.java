package com.example.lesson.controller;

import com.example.lesson.dto.CampaignMemberCreateRequest;
import com.example.lesson.dto.CampaignMemberCreateResponse;
import com.example.lesson.service.CampaignMemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campaign-members")
public class CampaignMemberController {
    private final CampaignMemberService campaignMemberService;

    public CampaignMemberController(CampaignMemberService campaignMemberService) {
        this.campaignMemberService = campaignMemberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignMemberCreateResponse create(@Valid @RequestBody CampaignMemberCreateRequest request) {
        return campaignMemberService.create(request);
    }
}
