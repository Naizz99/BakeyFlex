package com.naizzbakers.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naizzbakers.bms.model.TaskLog;
import com.naizzbakers.bms.repository.TaskLogRepository;

@Service
@Transactional
public class TaskLogService {

	@Autowired
	private TaskLogRepository
	repo;
	  
	public List<TaskLog> get() {
		return repo.findAll();
	}
	  
	public TaskLog get(long id) {
		return repo.findById(id).get();
	}
	
	public void save(TaskLog obj) {
		repo.save(obj);
	}
	  
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
