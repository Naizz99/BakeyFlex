package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class PasswordPolicyDto {
	private long policyId;
	private String policy;
	private String data;
	private boolean active;
}
