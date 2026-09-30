package com.example.lesson.repository;

import com.example.lesson.domain.CampaignMember;
import com.example.lesson.dto.PaymentSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CampaignMemberRepository extends JpaRepository<CampaignMember,Long> {
    @Query(
            value = """
        select new com.example.lesson.dto.PaymentSummaryResponse(
            cm.id,
            cm.applicantName,
            cm.expectedAmount,
                    sum(p.amount)
            )
        from CampaignMember cm
        left join Payment p on p.campaignMember = cm
        where cm.feeCampaign.id = :feeCampaignId
        group by cm.id, cm.applicantName, cm.expectedAmount
        order by cm.id
        """,
            countQuery = """
        select count(cm.id)
        from CampaignMember cm
        where cm.feeCampaign.id = :feeCampaignId
        """
    )
    Page<PaymentSummaryResponse> findPaymentSummaries(
            @Param("feeCampaignId") Long feeCampaignId,
            Pageable pageable
    );
}
