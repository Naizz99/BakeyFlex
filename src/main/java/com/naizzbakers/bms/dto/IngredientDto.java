package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class IngredientDto{
	
	private String ingredientId;
	private String recipeId;
	private String inventoryId;
	private String image;
	private String ingredientName;
	private String amount;
	private String statuc; 
	    
}
