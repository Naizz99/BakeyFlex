package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.PasswordPolicy;
import com.naizzbakers.bms.repository.PasswordPolicyRepository;

@Service
@Transactional
public class PasswordPolicyService {

	@Autowired
	private PasswordPolicyRepository repo;
	  
	public List<PasswordPolicy> get() {
		return repo.findAll();
	}
	  
	public PasswordPolicy get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(PasswordPolicy obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
