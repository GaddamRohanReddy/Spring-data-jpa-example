package com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.entity.Customer;
import com.jpaExample.repository.CustomerRepository;

@Component
public class CustomerRepositoryService implements CommandLineRunner {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer("George","Chennai"));
		List<Customer> customerList = customerRepository.findAll();
		System.out.println(customerList);
	}
	
}
