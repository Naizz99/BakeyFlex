package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class SupplierDto {
	private long supplierId;
	private String supplierSerial; 
	private String supplierName;
	private String contactPerson; 
	private String mobile1;
	private String mobile2;
	private String address;
	private String email; 
	private String image;
	private String specialNote;
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status; 
		    
}
