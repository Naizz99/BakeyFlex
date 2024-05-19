package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class OrderDto {

	private String orderId;
	private String orderCode; 
	private String productId;
	private String customerId;	
	private String orderedDate;
	private String quantity; 
	private String unitPrice; 
	private String totalPrice; 
	private String paid;
	private String specialNote; 
	private String deliveryNote;
	private String contactPerson; 
	private String alternativeMobile; 
	private String customerAddress; 
	private String addressLine1; 
	private String addressLine2; 
	private String addressLine3; 
	private String addressLine4; 
	private String acceptedBy;
	private String acceptedDate;
	private String processedBy;
	private String processedDate;
	private String packedBy;
	private String packedDate;
	private String deliveredBy;
	private String pickedDate;
	private String deliveredDate;
	private String canceledBy;
	private String canceledDate;
	private String status; 
		    
}
