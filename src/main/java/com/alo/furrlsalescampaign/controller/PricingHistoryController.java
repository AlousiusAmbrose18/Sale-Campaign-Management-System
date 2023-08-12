package com.alo.furrlsalescampaign.controller;

import com.alo.furrlsalescampaign.model.PricingHistory;
import com.alo.furrlsalescampaign.repository.PricingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}")
public class PricingHistoryController {

    @Autowired
    private PricingHistoryRepository pricingHistoryRepository;

    @GetMapping("/priceHistory")
    public ResponseEntity<List<PricingHistory>> getProductPriceHistory(@PathVariable String productId) {
        List<PricingHistory> priceHistory = pricingHistoryRepository.findByProductId(productId);
        return ResponseEntity.ok(priceHistory);
    }
}
