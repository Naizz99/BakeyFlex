package com.naizzbakers.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.ProductType;
import com.naizzbakers.bms.model.ProductType.STATUS;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

	List<ProductType> getByStatus(STATUS status);

}
