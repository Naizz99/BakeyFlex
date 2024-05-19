package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
