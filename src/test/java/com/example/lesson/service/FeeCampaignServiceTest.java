package com.example.lesson.service;

import com.example.lesson.dto.FeeCampaignCreateRequest;
import com.example.lesson.exception.BadRequestException;
import com.example.lesson.exception.GlobalExceptionHandler;
import com.example.lesson.repository.FeeCampaignRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

class FeeCampaignServiceTest {
    @Test
    void rejectsEndBeforeStartWithoutSaving() {
        FeeCampaignRepository repository = mock(FeeCampaignRepository.class);
        FeeCampaignService service = new FeeCampaignService(repository);
        Instant start = Instant.parse("2026-10-01T00:00:00Z");
        FeeCampaignCreateRequest request = new FeeCampaignCreateRequest(
                "해커톤 참가비", "HACKATHON", new BigDecimal("20000"), null,
                start, start.minusSeconds(1)
        );

        BadRequestException error = assertThrows(
                BadRequestException.class, () -> service.create(request)
        );

        assertEquals(HttpStatus.BAD_REQUEST, new GlobalExceptionHandler().handleBadRequest(error).getStatusCode());
        verifyNoInteractions(repository);
    }
}
