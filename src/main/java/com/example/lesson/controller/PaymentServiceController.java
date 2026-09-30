package com.example.lesson.controller;

import com.example.lesson.dto.PaymentCreateRequest;
import com.example.lesson.dto.PaymentCreateResponse;
import com.example.lesson.dto.PaymentSummaryResponse;
import com.example.lesson.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // 반환값이 json형태로 출력
@RequestMapping("/api")
public class PaymentServiceController {
    private final PaymentService paymentService;

    public PaymentServiceController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentCreateResponse> createPayment(
            @Valid @RequestBody PaymentCreateRequest request) {
        PaymentCreateResponse response = paymentService.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/campaign-members/{campaignMemberId}/payment-summary")
    public PaymentSummaryResponse getSummary(
            @PathVariable("campaignMemberId") Long campaignMemberId) {
        return paymentService.getSummary(campaignMemberId);
    }

    @GetMapping("/fee-campaigns/{feeCampaignId}/payment-summary")
    public Page<PaymentSummaryResponse> getSummaries(
            @PathVariable Long feeCampaignId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return paymentService.getSummaries(
                feeCampaignId,
                PageRequest.of(page,size)
        );
    }
}

