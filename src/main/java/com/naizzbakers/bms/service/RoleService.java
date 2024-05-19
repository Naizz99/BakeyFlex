package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Role;
import com.naizzbakers.bms.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository repo;
	  
	public List<Role> get() {
		return repo.findAll();
	}
	  
	public Role get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Role obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<Role> listAll() {
		return repo.findAll();
	}

	public Role getByRole(String role) {
		return repo.getByRole(role);
	}
	
}
