package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Notification;
import com.naizzbakers.bms.repository.NotificationRepository;

@Service
@Transactional
public class NotificationService {

	@Autowired
	private NotificationRepository repo;
	  
	public List<Notification> get() {
		return repo.findAll();
	}
	  
	public Notification get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Notification obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
