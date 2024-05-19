package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Ingredient;
import com.naizzbakers.bms.repository.IngredientRepository;



@Service
@Transactional
public class IngredientService {

	@Autowired
	private IngredientRepository repo;
	  
	public List<Ingredient> get() {
		return repo.findAll();
	}
	  
	public Ingredient get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Ingredient obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
