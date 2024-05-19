package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
