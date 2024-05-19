package com.naizzbakers.bms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Inventory;
import com.naizzbakers.bms.model.InventoryRequest;
import com.naizzbakers.bms.model.InventoryRequest.STATUS;

public interface InventoryRequestRepository extends JpaRepository<InventoryRequest, Long> {

	List<InventoryRequest> getByInventoryId(Inventory inventory);

	List<InventoryRequest> getByInventoryIdAndStatus(Inventory inventory, STATUS status);

}
