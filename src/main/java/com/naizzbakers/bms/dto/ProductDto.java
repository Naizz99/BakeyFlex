package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class ProductDto {
	private long productId;
	private String productSerial; 
	private long productType;
	private String productName; 
	private String shortDescription; 
	private String longDescription;
	private String specialNote;
	private String image;  
	private double unitPrice;  
	private double discountRate;  
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status; 
	private long recipeId;	    
}
