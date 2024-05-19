package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class MessageDto {
	private String messageId;
	private String sentFrom;
	private String sentTo;
	private String sentDate;
	private String sentTime;
	private String message;
	private String readStatus;
}
