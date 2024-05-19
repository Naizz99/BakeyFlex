package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
