package com.alo.furrlsalescampaign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FurrlSalesCampaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurrlSalesCampaignApplication.class, args);
	}

}
