package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Feedback;
import com.naizzbakers.bms.repository.FeedbackRepository;



@Service
@Transactional
public class FeedbackService {

	@Autowired
	private FeedbackRepository repo;
	  
	public List<Feedback> get() {
		return repo.findAll();
	}
	  
	public Feedback get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Feedback obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
