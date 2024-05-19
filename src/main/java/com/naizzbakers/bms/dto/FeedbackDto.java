package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class FeedbackDto{

	private String feedbackid;
	private String customerId;
	private String orderId;
	private String review;
	private String productRate;
	private String packingRate;
	private String deliveryRate;

	
}
