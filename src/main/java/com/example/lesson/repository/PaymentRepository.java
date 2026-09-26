package com.example.lesson.repository;

import com.example.lesson.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("""
           select sum(p.amount)
           from Payment p
           where p.campaignMember.id = :campaignMemberId
           """)
    Optional<BigDecimal> sumPaidAmount(
            @Param("campaignMemberId") Long campaignMemberId);
}