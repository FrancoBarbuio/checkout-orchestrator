package com.ecommerce.checkout_orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CheckoutOrchestratorApplication {

	public static void main(String[] args) {

		SpringApplication.run(CheckoutOrchestratorApplication.class, args);
	}

}
