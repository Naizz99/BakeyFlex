package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Product;
import com.naizzbakers.bms.repository.ProductRepository;


@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository repo;
	  
	public List<Product> get() {
		return repo.findAll();
	}
	  
	public Product get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Product obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}