package com.naizzbakers.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	List<CustomerOrder> getByCustomerId(Long customerId);

}
