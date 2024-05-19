package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.naizzbakers.bms.model.InventoryType;
import com.naizzbakers.bms.model.InventoryType.STATUS;

public interface InventoryTypeRepository extends JpaRepository<InventoryType, Long> {

	List<InventoryType> findByStatus(STATUS active);

}
