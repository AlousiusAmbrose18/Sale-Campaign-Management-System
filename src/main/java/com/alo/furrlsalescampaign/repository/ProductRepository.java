package com.alo.furrlsalescampaign.repository;

import com.alo.furrlsalescampaign.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
