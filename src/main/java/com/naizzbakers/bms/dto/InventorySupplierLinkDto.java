package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class InventorySupplierLinkDto {
	private long islId;
	private long inventoryId;
	private String supplierId;	
	private String specialNote;
	private String image;  
	private int availableAmount;
	private double unitPrice;
	private String firstExDate;
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status; 
		    
}
