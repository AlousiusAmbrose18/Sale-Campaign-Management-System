package com.alo.furrlsalescampaign.util;

import com.alo.furrlsalescampaign.model.Product;
import com.alo.furrlsalescampaign.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) {
        // Load initial data
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setId("jeiu8f" + i);
            product.setTitle("Product " + i);
            product.setDescription("Description for Product " + i);
            product.setMrp(1000.0 + i * 10);
            product.setCurrentPrice(900.0 + i * 10);
            product.setDiscount(10.0);
            product.setInventoryCount(2);
            productRepository.save(product);
        }
    }
}
