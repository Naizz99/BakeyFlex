package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Employee;
import com.naizzbakers.bms.repository.EmployeeRepository;



@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	  
	public List<Employee> get() {
		return repo.findAll();
	}
	  
	public Employee get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Employee obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
