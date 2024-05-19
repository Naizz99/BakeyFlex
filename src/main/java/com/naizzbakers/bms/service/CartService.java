package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Cart;
import com.naizzbakers.bms.repository.CartRepository;

@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepository repo;
	  
	public List<Cart> get() {
		return repo.findAll();
	}
	  
	public Cart get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Cart obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
