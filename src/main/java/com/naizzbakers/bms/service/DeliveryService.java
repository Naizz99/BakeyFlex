package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Delivery;
import com.naizzbakers.bms.repository.DeliveryRepository;

@Service
@Transactional
public class DeliveryService {

	@Autowired
	private DeliveryRepository repo;
	  
	public List<Delivery> get() {
		return repo.findAll();
	}
	  
	public Delivery get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Delivery obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
