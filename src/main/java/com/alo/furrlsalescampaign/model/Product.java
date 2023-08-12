package com.alo.furrlsalescampaign.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private Double mrp;
    private Double currentPrice;
    private Double discount;
    private Integer inventoryCount;

}