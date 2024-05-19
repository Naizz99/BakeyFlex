package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.CustomerOrder;
import com.naizzbakers.bms.repository.CustomerOrderRepository;

@Service
@Transactional
public class CustomerOrderService {

	@Autowired
	private CustomerOrderRepository repo;
	  
	public List<CustomerOrder> get() {
		return repo.findAll();
	}
	  
	public CustomerOrder get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(CustomerOrder obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<CustomerOrder> getByCustomerId(Long customerId) {
		return repo.getByCustomerId(customerId);
	}
	
}
