package com.alo.furrlsalescampaign.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaleCampaign implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(targetEntity = CampaignDiscount.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "campaign", referencedColumnName = "id")
    private List<CampaignDiscount> campaignDiscounts;

    private Boolean isDiscount;

}
