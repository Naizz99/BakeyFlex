package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

}
