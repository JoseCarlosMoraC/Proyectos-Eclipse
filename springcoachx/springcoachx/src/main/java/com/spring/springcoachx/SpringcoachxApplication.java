package com.spring.springcoachx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.spring", "exceptions"})
@EntityScan(basePackages = "com.spring.models")
@EnableJpaRepositories(basePackages = "com.spring.repositories")
public class SpringcoachxApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringcoachxApplication.class, args);
	}
}