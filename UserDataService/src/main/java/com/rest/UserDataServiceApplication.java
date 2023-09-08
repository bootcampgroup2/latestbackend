package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.repository.UserRepository;

@SpringBootApplication(scanBasePackages = {"com.controller","com.service","com.exception"})
@EnableMongoRepositories("com.repository")

public class UserDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDataServiceApplication.class, args);
	}

}
