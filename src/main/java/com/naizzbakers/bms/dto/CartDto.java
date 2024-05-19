package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class CartDto {
	
	private String cartId;
    private String customerId;
    private String productId;
	private String quantity;
	private String unitPrice;
	private String totalAmount;
	private String createDate;
	private String updateDate;
	
}
