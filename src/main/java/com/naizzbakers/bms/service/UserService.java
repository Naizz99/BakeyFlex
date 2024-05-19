package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;
	  
	public List<User> get() {
		return repo.findAll();
	}
	  
	public User get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(User obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}
}
