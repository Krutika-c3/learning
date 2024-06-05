package com.application.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.application.example.entity")
@EnableJpaRepositories("com.application.example.repository")
public class GraphQLWithSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(GraphQLWithSpringBootApplication.class, args);
	}

}
