package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
}
