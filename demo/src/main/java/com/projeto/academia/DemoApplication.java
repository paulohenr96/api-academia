package com.projeto.academia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.projeto.academia.repository")
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan (basePackages = {"com.projeto.academia.controller",
									"com.projeto.academia.service",
									"com.projeto.academia.security",
									"com.projeto.academia.exception.controlleradvice"
									})
@EntityScan(basePackages = "com.projeto.academia.model")

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
