package com.example.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.repo.CustomerRepository;

@RestController
public class WebController {

	@Autowired
	CustomerRepository repository;

	@RequestMapping("/save")
	public HashMap<String, String> process() {
		HashMap<String, String> hashMap = new HashMap<>();
		System.out.println("--saving data--");
		repository.save(new Customer("Jack", "Smith"));
		return hashMap;
	}

	@RequestMapping("/findall")
	public String findAll() {
		System.out.println("--findall--");
		String result = "";

		for (Customer cust : repository.findAll()) {
			result += cust.toString() + "<br>";
			System.out.println(cust.getFirstName());
		}

		return result;
	}

	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id) {
		String result = "";
		result = repository.findById(id).toString();
		return result;
	}

	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName) {
		String result = "";

		for (Customer cust : repository.findByLastName(lastName)) {
			result += cust.toString() + "<br>";
		}

		return result;
	}
}