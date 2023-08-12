package com.alo.furrlsalescampaign.controller;

import com.alo.furrlsalescampaign.model.SaleCampaign;
import com.alo.furrlsalescampaign.service.SaleCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class SaleCampaignController {
    @Autowired
    private SaleCampaignService saleCampaignService;

    @PostMapping("/create")
    public ResponseEntity<String> createSaleCampaign(@RequestBody SaleCampaign request) {
        if (request != null) {
            return ResponseEntity.ok(saleCampaignService.createSaleCampaign(request));
        }
        return ResponseEntity.ofNullable(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleCampaign> getSaleCampaignById(@PathVariable Long id) {
        SaleCampaign saleCampaign = saleCampaignService.getSaleCampaignById(id);
        if (saleCampaign != null)
            return ResponseEntity.ok(saleCampaign);
        return ResponseEntity.ofNullable(null);
    }

    @GetMapping
    public ResponseEntity<List<SaleCampaign>> getAllSaleCampaigns() {
        List<SaleCampaign> campaigns = saleCampaignService.getAllSaleCampaigns();
        return ResponseEntity.ok(campaigns);
    }

}


