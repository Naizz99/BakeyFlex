package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class TaskLogDto{
	private String taskId;
	private String employeeId;
	private String orderId;
	private String task;
	private String log;
	private String createDate;
	
}
