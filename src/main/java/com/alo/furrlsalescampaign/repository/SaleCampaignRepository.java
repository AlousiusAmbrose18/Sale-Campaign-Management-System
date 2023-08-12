package com.alo.furrlsalescampaign.repository;

import com.alo.furrlsalescampaign.model.SaleCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleCampaignRepository extends JpaRepository<SaleCampaign, Long> {
    List<SaleCampaign> findByEndDate(LocalDate currentDate);
}
