package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class TransactionDto {
	private String transactionId;
	private String transactionCode; 
	private String crdr; 
	private String amount; 
	private String shortDescription; 
	private String description; 
	private String specialNote;
	private String image;  
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
				    
}
