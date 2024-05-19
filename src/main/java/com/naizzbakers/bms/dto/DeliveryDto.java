package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class DeliveryDto{

	private String deliveryId;
	private String orderId;
	private String delivererId;
	private String pickedDate;	
	private String deliveredDate;
	private String delivereryNote;
	private String status; 
		    
}
