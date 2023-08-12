package com.alo.furrlsalescampaign.controller;

import com.alo.furrlsalescampaign.Dto.ProductDto;
import com.alo.furrlsalescampaign.model.Product;
import com.alo.furrlsalescampaign.repository.PricingHistoryRepository;
import com.alo.furrlsalescampaign.repository.ProductRepository;
import com.alo.furrlsalescampaign.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PricingHistoryRepository pricingHistoryRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProducts(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Page<ProductDto> list = productService.getProducts(page, pageSize);
        if (list != null && !list.isEmpty()) return ResponseEntity.ok(list);
        return null;
    }

    @PutMapping("/{productId}/update-price")
    public ResponseEntity<String> updateProductPrice(@PathVariable String productId, @RequestParam double newPrice) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setCurrentPrice(newPrice);
        productRepository.save(product);


        return ResponseEntity.ok("Product price updated successfully.");
    }


}
