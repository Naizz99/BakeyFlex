package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class CustomerDto{
	private long customerId;
	private String customerCode;
	private String firstName; 
	private String lastName; 
	private String mobile;
	private String email; 
	private String addressLine1; 
	private String addressLine2; 
	private String addressLine3; 
	private String addressLine4; 
	private String gender;
	private String birthDay;
	private String image;
	private String username;
	private String password;
	private String logged; 
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status; 
	    
}
