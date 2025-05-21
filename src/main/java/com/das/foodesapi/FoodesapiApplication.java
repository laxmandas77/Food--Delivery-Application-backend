package com.das.foodesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.das.foodesapi.repositry")
public class FoodesapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodesapiApplication.class, args);
	}

}
