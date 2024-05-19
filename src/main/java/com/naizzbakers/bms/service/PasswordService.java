package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Password;
import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.repository.PasswordRepository;

@Service
@Transactional
public class PasswordService {

	@Autowired
	private PasswordRepository repo;
	  
	public List<Password> get() {
		return repo.findAll();
	}
	  
	public Password get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Password obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}

	public Password findByUserId(User userId) {
		return repo.findByUserId(userId);
	}

	public void deleteByUserId(User userId) {
		repo.deleteByUserId(userId);
	}

	public Password getByUserId(User user) {
		return repo.getByUserId(user);
	}
	
}
