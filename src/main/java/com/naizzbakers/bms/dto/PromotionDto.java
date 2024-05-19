package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class PromotionDto {
	private String promotionId;
	private String promoCode;
	private String startDate;
	private String endDate;
	private String counting;
	private String title;
	private String description;
	private String specialNote;
	private String image;
	private String backgroundImage;
	private String onlyImage;
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status;
}
