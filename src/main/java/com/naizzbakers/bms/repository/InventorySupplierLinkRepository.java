package com.naizzbakers.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Inventory;
import com.naizzbakers.bms.model.InventorySupplierLink;

public interface InventorySupplierLinkRepository extends JpaRepository<InventorySupplierLink, Long> {

	List<InventorySupplierLink> findByInventoryId(Inventory inventory);

}
