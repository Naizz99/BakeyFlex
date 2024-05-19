package com.naizzbakers.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Supplier;
import com.naizzbakers.bms.model.Supplier.STATUS;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	List<Supplier> findByStatus(STATUS sts);

	Supplier findBySupplierSerial(String serial);

}
