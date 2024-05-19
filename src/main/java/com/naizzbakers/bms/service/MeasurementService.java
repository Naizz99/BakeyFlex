package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Measurement;
import com.naizzbakers.bms.model.Measurement.STATUS;
import com.naizzbakers.bms.repository.MeasurementRepository;



@Service
@Transactional
public class MeasurementService {

	@Autowired
	private MeasurementRepository repo;
	  
	public List<Measurement> get() {
		return repo.findAll();
	}
	  
	public Measurement get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Measurement obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<Measurement> getByStatus(STATUS active) {
		return repo.findByStatus(active);
	}
	
}
