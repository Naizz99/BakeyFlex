package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Profile;
import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.repository.ProfileRepository;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository repo;
	  
	public List<Profile> get() {
		return repo.findAll();
	}
	  
	public Profile get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Profile obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}

	public Profile getByUserId(User user) {
		return repo.getByUserId(user);
	}
	
}
