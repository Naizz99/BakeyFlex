package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Customer;
import com.naizzbakers.bms.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	  
	public List<Customer> get() {
		return repo.findAll();
	}
	  
	public Customer get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Customer obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
