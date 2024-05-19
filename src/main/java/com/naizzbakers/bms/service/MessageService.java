package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Message;
import com.naizzbakers.bms.repository.MessageRepository;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository
	repo;
	  
	public List<Message> get() {
		return repo.findAll();
	}
	  
	public Message get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Message obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
