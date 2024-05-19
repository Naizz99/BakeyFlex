package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class UserFunctionDto {
	private String functionId;
	private String functionName;
	private String functionAction;
	private String isParent;
	private String parentId;
	private String linked;
	private String active;
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	
}
