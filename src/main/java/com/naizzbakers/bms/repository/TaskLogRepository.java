package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.TaskLog;

public interface TaskLogRepository extends JpaRepository<TaskLog, Long> {

}
