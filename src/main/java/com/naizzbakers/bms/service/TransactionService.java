package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.Transaction;
import com.naizzbakers.bms.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository repo;
	  
	public List<Transaction> get() {
		return repo.findAll();
	}
	  
	public Transaction get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(Transaction obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
