package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.UserFunction;
import com.naizzbakers.bms.repository.UserFunctionRepository;


@Service
@Transactional
public class UserFunctionService {

	@Autowired
	private UserFunctionRepository repo;
	  
	public List<UserFunction> get() {
		return repo.findAll();
	}
	  
	public UserFunction get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(UserFunction obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
