package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class InventoryRequestDto {
	private long irId;
	private String requestCode;
	private long inventoryId;
	private String supplierId;	
	private String specialNote;	
	private int requestedAmount;
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status; 
		    
}
