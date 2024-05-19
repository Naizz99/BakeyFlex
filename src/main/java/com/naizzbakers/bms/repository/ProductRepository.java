package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
