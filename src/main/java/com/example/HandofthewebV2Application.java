package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.repo.CustomerRepository;

@SpringBootApplication
public class HandofthewebV2Application implements CommandLineRunner {
	@Autowired
	CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(HandofthewebV2Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// clear all record if existed before do the tutorial with new data
		repository.deleteAll();
	}
}
