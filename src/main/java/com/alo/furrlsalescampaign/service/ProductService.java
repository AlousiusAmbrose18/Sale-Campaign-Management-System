package com.alo.furrlsalescampaign.service;


import com.alo.furrlsalescampaign.Dto.ProductDto;
import com.alo.furrlsalescampaign.model.Product;
import com.alo.furrlsalescampaign.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDto> getProducts(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Product> products = productRepository.findAll(pageable);
        return convertPage(products);
    }

    private static Page<ProductDto> convertPage(Page<Product> productPage) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setMrp(product.getMrp());
            productDto.setCurrentPrice(product.getCurrentPrice());
            productDto.setDiscount(product.getDiscount());
            productDto.setInventoryCount(product.getInventoryCount());
            productDtoList.add(productDto);
        }
        return new PageImpl<>(productDtoList, productPage.getPageable(), productPage.getTotalElements());
    }

}

