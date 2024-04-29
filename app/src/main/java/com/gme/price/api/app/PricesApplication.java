package com.gme.price.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.gme.price.api")
@EntityScan(basePackages = "com.gme.price.api")
@EnableJpaRepositories(basePackages = "com.gme.price.api")
public class PricesApplication {
	public static void main(String[] args) {
		SpringApplication.run(PricesApplication.class, args);
	}
}
