package com.example.lesson.repository;

import com.example.lesson.domain.CampaignMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignMemberRepository extends JpaRepository<CampaignMember,Long> {
}
