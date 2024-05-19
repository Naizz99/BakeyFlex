package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.ProductImage;
import com.naizzbakers.bms.repository.ProductImageRepository;

@Service
@Transactional
public class ProductImageService {

	@Autowired
	private ProductImageRepository repo;
	  
	public List<ProductImage> get() {
		return repo.findAll();
	}
	  
	public ProductImage get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(ProductImage obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
