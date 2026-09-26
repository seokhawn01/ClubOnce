package com.example.lesson.service;

import com.example.lesson.domain.FeeCampaign;
import com.example.lesson.domain.FeeCampaignStaus;
import com.example.lesson.dto.FeeCampaignCreateRequest;
import com.example.lesson.dto.FeeCampaignCreateResponse;
import com.example.lesson.exception.BadRequestException;
import com.example.lesson.repository.FeeCampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeeCampaignService {
    private final FeeCampaignRepository feeCampaignRepository;

    public FeeCampaignService(FeeCampaignRepository feeCampaignRepository) {
        this.feeCampaignRepository = feeCampaignRepository;
    }

    @Transactional
    public FeeCampaignCreateResponse create(FeeCampaignCreateRequest request) {
        if (!request.startsAt().isBefore(request.endsAt())) {
            throw new BadRequestException("납부 마감 시각은 시작 시각보다 늦어야 합니다.");
        }

        FeeCampaign campaign = new FeeCampaign(
                request.name(), request.category(), request.defaultAmount(), request.capacity(),
                FeeCampaignStaus.OPEN, request.startsAt(), request.endsAt()
        );
        return new FeeCampaignCreateResponse(feeCampaignRepository.save(campaign).getId());
    }
}
