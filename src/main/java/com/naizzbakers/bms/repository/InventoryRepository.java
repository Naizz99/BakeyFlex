package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
