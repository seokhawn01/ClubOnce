package com.example.lesson.service;

import com.example.lesson.domain.CampaignMember;
import com.example.lesson.domain.Payment;
import com.example.lesson.dto.PaymentCreateRequest;
import com.example.lesson.dto.PaymentCreateResponse;
import com.example.lesson.dto.PaymentSummaryResponse;
import com.example.lesson.exception.NotFoundException;
import com.example.lesson.repository.CampaignMemberRepository;
import com.example.lesson.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service //
public class PaymentService {

    private final CampaignMemberRepository campaignMemberRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(CampaignMemberRepository campaignMemberRepository,
                          PaymentRepository paymentRepository) {
        this.campaignMemberRepository = campaignMemberRepository;
        this.paymentRepository = paymentRepository;
    }
    // CampaignMember가 금액을 낸 표기(한건)
    @Transactional
    public PaymentCreateResponse createPayment(PaymentCreateRequest request) {
        CampaignMember campaignMember =
                findCampaignMember(request.campaignMemberId());

        Payment payment = new Payment(
                campaignMember,
                request.payerName(),
                request.amount(),
                request.paidAt()
        );

        Payment saved = paymentRepository.save(payment);
        return new PaymentCreateResponse(saved.getId());
    }

    @Transactional(readOnly = true) // 조회만 하는 작업
    public PaymentSummaryResponse getSummary(Long campaignMemberId) {
        CampaignMember campaignMember = findCampaignMember(campaignMemberId);

        BigDecimal paidAmount = paymentRepository
                .sumPaidAmount(campaignMemberId)
                .orElse(BigDecimal.ZERO); // 입금 내역이 없으면 합계는 0원

        return new PaymentSummaryResponse(
                campaignMember.getId(),
                campaignMember.getApplicantName(),
                campaignMember.getExpectedAmount(),
                paidAmount
        );
    }

    private CampaignMember findCampaignMember(Long id) {
        return campaignMemberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "납부 대상을 찾을 수 없습니다. id=" + id
                ));
    }
}