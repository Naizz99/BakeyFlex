package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.RoleFunctionLink;
import com.naizzbakers.bms.repository.RoleFunctionLinkRepository;

@Service
@Transactional
public class RoleFunctionLinkService {

	@Autowired
	private RoleFunctionLinkRepository repo;
	  
	public List<RoleFunctionLink> get() {
		return repo.findAll();
	}
	  
	public RoleFunctionLink get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(RoleFunctionLink obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
