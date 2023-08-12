package com.alo.furrlsalescampaign.Dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @Id
    private String id;
    private Double mrp;
    private Double currentPrice;
    private Double discount;
    private Integer inventoryCount;
}
