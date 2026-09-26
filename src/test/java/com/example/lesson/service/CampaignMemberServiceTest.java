package com.example.lesson.service;

import com.example.lesson.domain.*;
import com.example.lesson.dto.CampaignMemberCreateRequest;
import com.example.lesson.repository.CampaignMemberRepository;
import com.example.lesson.repository.FeeCampaignRepository;
import com.example.lesson.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CampaignMemberServiceTest {
    @Test
    void copiesCampaignAmountWhenRegisteringMember() {
        MemberRepository memberRepository = mock(MemberRepository.class);
        FeeCampaignRepository feeCampaignRepository = mock(FeeCampaignRepository.class);
        CampaignMemberRepository campaignMemberRepository = mock(CampaignMemberRepository.class);
        Member member = new Member("가상회원", "member@example.test", true, MemberRole.MEMBER, MemberStatus.ACTIVE);
        FeeCampaign campaign = new FeeCampaign(
                "해커톤 참가비", "HACKATHON", new BigDecimal("20000"), null,
                FeeCampaignStaus.OPEN, Instant.parse("2026-10-01T00:00:00Z"),
                Instant.parse("2026-10-31T00:00:00Z")
        );
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(feeCampaignRepository.findById(2L)).thenReturn(Optional.of(campaign));
        when(campaignMemberRepository.save(any(CampaignMember.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        new CampaignMemberService(memberRepository, feeCampaignRepository, campaignMemberRepository)
                .create(new CampaignMemberCreateRequest(1L, 2L));

        ArgumentCaptor<CampaignMember> saved = ArgumentCaptor.forClass(CampaignMember.class);
        verify(campaignMemberRepository).save(saved.capture());
        assertSame(member, saved.getValue().getMember());
        assertSame(campaign, saved.getValue().getFeeCampaign());
        assertEquals(new BigDecimal("20000"), saved.getValue().getExpectedAmount());
    }
}
