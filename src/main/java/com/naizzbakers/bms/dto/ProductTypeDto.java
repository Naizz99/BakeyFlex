package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class ProductTypeDto {
	private long ptId;
	private String productType; 
	private String productTypeName; 
	private String description;  
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status; 
	    
}
