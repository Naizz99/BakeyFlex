package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.RecipeTask;
import com.naizzbakers.bms.repository.RecipeTaskRepository;

@Service
@Transactional
public class RecipeTaskService {

	@Autowired
	private RecipeTaskRepository repo;
	  
	public List<RecipeTask> get() {
		return repo.findAll();
	}
	  
	public RecipeTask get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(RecipeTask obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
