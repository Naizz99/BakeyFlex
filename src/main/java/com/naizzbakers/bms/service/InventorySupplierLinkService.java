package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Inventory;
import com.naizzbakers.bms.model.InventorySupplierLink;
import com.naizzbakers.bms.repository.InventorySupplierLinkRepository;



@Service
@Transactional
public class InventorySupplierLinkService {

	@Autowired
	private InventorySupplierLinkRepository repo;
	  
	public List<InventorySupplierLink> get() {
		return repo.findAll();
	}
	  
	public InventorySupplierLink get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(InventorySupplierLink obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public List<InventorySupplierLink> getByInventoryId(Inventory inventory) {
		return repo.findByInventoryId(inventory);
	}
}
