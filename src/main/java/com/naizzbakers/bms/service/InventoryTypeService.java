package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.InventoryType;
import com.naizzbakers.bms.model.InventoryType.STATUS;
import com.naizzbakers.bms.repository.InventoryTypeRepository;



@Service
@Transactional
public class InventoryTypeService {

	@Autowired
	private InventoryTypeRepository repo;
	  
	public List<InventoryType> get() {
		return repo.findAll();
	}
	  
	public InventoryType get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(InventoryType obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<InventoryType> getByStatus(STATUS active) {
		return repo.findByStatus(active);
	}
	
}
