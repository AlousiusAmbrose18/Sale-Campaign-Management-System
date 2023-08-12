package com.alo.furrlsalescampaign.repository;

import com.alo.furrlsalescampaign.model.PricingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PricingHistoryRepository extends JpaRepository<PricingHistory, Long> {
    List<PricingHistory> findByProductId(String productId);
}
