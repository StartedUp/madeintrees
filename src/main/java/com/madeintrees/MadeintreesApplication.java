package com.madeintrees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.madeintrees")
public class MadeintreesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadeintreesApplication.class, args);
	}
}
