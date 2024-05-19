package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class ProductImageDto{
	private String piId;
	private String productId;
	private String image;
	private String alternativeText;
	private String active; 
	    
}
