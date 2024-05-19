package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class NotificationDto {
	private String ntfId;
	private String userId;
	private String receiveDate;
	private String receiveTime;
	private String topic;
	private String notification;
	private String readStatus;
	
}
