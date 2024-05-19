package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Promotion;
import com.naizzbakers.bms.repository.PromotionRepository;

@Service
@Transactional
public class PromotionService {

	@Autowired
	private PromotionRepository repo;
	  
	public List<Promotion> get() {
		return repo.findAll();
	}
	  
	public Promotion get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Promotion obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
