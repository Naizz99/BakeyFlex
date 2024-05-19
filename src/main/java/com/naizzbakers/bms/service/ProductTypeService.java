package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.ProductType;
import com.naizzbakers.bms.model.ProductType.STATUS;
import com.naizzbakers.bms.repository.ProductTypeRepository;


@Service
@Transactional
public class ProductTypeService {

	@Autowired
	private ProductTypeRepository repo;
	  
	public List<ProductType> get() {
		return repo.findAll();
	}
	  
	public ProductType get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(ProductType obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<ProductType> getByStatus(STATUS status) {
		return repo.getByStatus(status);
	}
	
}
