package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Recipe;
import com.naizzbakers.bms.model.Recipe.STATUS;
import com.naizzbakers.bms.repository.RecipeRepository;

@Service
@Transactional
public class RecipeService {

	@Autowired
	private RecipeRepository repo;
	  
	public List<Recipe> get() {
		return repo.findAll();
	}
	  
	public Recipe get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Recipe obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}

	public Object getByStatus(STATUS status) {
		return repo.getByStatus(status);
	}
	
}
