package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class RecipeDto {
	private long recipeId;
	private String description;
	private String specialNote;
	private String image;  
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	private String status; 
	
}
