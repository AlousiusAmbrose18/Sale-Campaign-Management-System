package com.alo.furrlsalescampaign.service;

import com.alo.furrlsalescampaign.model.CampaignDiscount;
import com.alo.furrlsalescampaign.model.PricingHistory;
import com.alo.furrlsalescampaign.model.Product;
import com.alo.furrlsalescampaign.model.SaleCampaign;
import com.alo.furrlsalescampaign.repository.PricingHistoryRepository;
import com.alo.furrlsalescampaign.repository.ProductRepository;
import com.alo.furrlsalescampaign.repository.SaleCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleCampaignService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleCampaignRepository saleCampaignRepository;

    @Autowired
    private PricingHistoryRepository pricingHistoryRepository;

    public String createSaleCampaign(SaleCampaign request) {

            for (CampaignDiscount discount : request.getCampaignDiscounts()) {
                Product product = productRepository.findById(discount.getProductId()).orElse(null);
                if (product != null) {
                    double discountedPrice = product.getCurrentPrice() * (1 - Double.parseDouble(discount.getDiscount()) / 100);
                    product.setCurrentPrice(discountedPrice);
                    productRepository.save(product);

                    PricingHistory priceChange = new PricingHistory();
                    priceChange.setProductId(product.getId());
                    priceChange.setPrice(product.getCurrentPrice());
                    priceChange.setTimestamp(LocalDateTime.now());
                    pricingHistoryRepository.save(priceChange);
                }
            }
            request.setIsDiscount(true);
            saleCampaignRepository.save(request);
            return "Sale campaign created successfully.";
    }


    //getting sale campaign by id and checking Sale campaign end Date
    public SaleCampaign getSaleCampaignById( Long id) {
        SaleCampaign saleCampaign = saleCampaignRepository.findById(id).orElse(null);
        if (saleCampaign == null || !saleCampaign.getIsDiscount()) return null;
        if (isSaleEnd(saleCampaign))
            return restoreProductPrice(saleCampaign);
        return saleCampaign;
    }


    public List<SaleCampaign> getAllSaleCampaigns() {
        List<SaleCampaign> campaigns = saleCampaignRepository.findAll();
        return campaigns;
    }


    //checks Sale end Date
    private Boolean isSaleEnd(SaleCampaign saleCampaign) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(saleCampaign.getEndDate());
    }


    //restoring Product price after SaleCampaign end
    private SaleCampaign restoreProductPrice(SaleCampaign saleCampaign) {
        for (CampaignDiscount discount : saleCampaign.getCampaignDiscounts()) {
            Product product = productRepository.findById(discount.getProductId()).orElse(null);
            if (product != null) {
                product.setCurrentPrice(product.getCurrentPrice() / (1 - Double.parseDouble(discount.getDiscount()) / 100)); // Revert to original price
                productRepository.save(product);

                PricingHistory priceChange = new PricingHistory();
                priceChange.setProductId(product.getId());
                priceChange.setPrice(product.getCurrentPrice());
                priceChange.setTimestamp(LocalDateTime.now());
                pricingHistoryRepository.save(priceChange);
            }
        }
        saleCampaign.setIsDiscount(false);
        saleCampaignRepository.save(saleCampaign);
        return saleCampaignRepository.findById(saleCampaign.getId()).orElse(null);
    }
}
