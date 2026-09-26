package com.example.lesson.service;

import com.example.lesson.domain.CampaignMember;
import com.example.lesson.domain.FeeCampaign;
import com.example.lesson.domain.Member;
import com.example.lesson.dto.CampaignMemberCreateRequest;
import com.example.lesson.dto.CampaignMemberCreateResponse;
import com.example.lesson.exception.NotFoundException;
import com.example.lesson.repository.CampaignMemberRepository;
import com.example.lesson.repository.FeeCampaignRepository;
import com.example.lesson.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CampaignMemberService {
    private final MemberRepository memberRepository;
    private final FeeCampaignRepository feeCampaignRepository;
    private final CampaignMemberRepository campaignMemberRepository;

    public CampaignMemberService(MemberRepository memberRepository,
                                 FeeCampaignRepository feeCampaignRepository,
                                 CampaignMemberRepository campaignMemberRepository) {
        this.memberRepository = memberRepository;
        this.feeCampaignRepository = feeCampaignRepository;
        this.campaignMemberRepository = campaignMemberRepository;
    }

    @Transactional
    public CampaignMemberCreateResponse create(CampaignMemberCreateRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new NotFoundException("멤버를 찾을 수 없습니다."));
        FeeCampaign campaign = feeCampaignRepository.findById(request.feeCampaignId())
                .orElseThrow(() -> new NotFoundException("납부 항목을 찾을 수 없습니다."));

        CampaignMember campaignMember = new CampaignMember(member, campaign);
        return new CampaignMemberCreateResponse(campaignMemberRepository.save(campaignMember).getId());
    }
}
