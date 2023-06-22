package com.projeto.academia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.projeto.academia.repository.UsuarioRepository;
import com.projeto.academia.service.UsuarioDetailsService;


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
	}

}
