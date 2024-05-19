package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
