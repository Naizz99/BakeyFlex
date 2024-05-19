package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Inventory;
import com.naizzbakers.bms.repository.InventoryRepository;



@Service
@Transactional
public class InventoryService {

	@Autowired
	private InventoryRepository repo;
	  
	public List<Inventory> get() {
		return repo.findAll();
	}
	  
	public Inventory get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Inventory obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
