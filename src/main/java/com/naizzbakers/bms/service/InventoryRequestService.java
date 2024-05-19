package com.naizzbakers.bms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Inventory;
import com.naizzbakers.bms.model.InventoryRequest;
import com.naizzbakers.bms.model.InventoryRequest.STATUS;
import com.naizzbakers.bms.repository.InventoryRequestRepository;

@Service
@Transactional
public class InventoryRequestService {

	@Autowired
	private InventoryRequestRepository repo;
	  
	public List<InventoryRequest> get() {
		return repo.findAll();
	}
	  
	public InventoryRequest get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(InventoryRequest obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public List<InventoryRequest> getByInventoryId(Inventory inventory) {
		return repo.getByInventoryId(inventory);
	}

	public List<InventoryRequest> getByInventoryIdAndStatus(Inventory inventory, STATUS status) {
		return repo.getByInventoryIdAndStatus(inventory, status);
	}
	
}
