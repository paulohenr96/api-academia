package com.projeto.academia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@ComponentScan(basePackages =  {"com.projeto.academia.controller",
//		"com.projeto.academia.service",
//		"com.projeto.academia.security",
//		"com.projeto.academia.exception"})
@EnableAutoConfiguration
@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.projeto.academia.repository"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
//		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}

}
