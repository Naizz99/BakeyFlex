package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class InventoryTypeDto {
	private String itId;
	private String inventoryType; 
	private String inventoryTypeName; 
	private String description;  
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status; 
	    
}
