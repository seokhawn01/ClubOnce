package com.example.lesson.repository;

import com.example.lesson.domain.FeeCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeCampaignRepository extends JpaRepository<FeeCampaign,Long> {
}
