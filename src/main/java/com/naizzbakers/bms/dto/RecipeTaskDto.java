package com.naizzbakers.bms.dto;

import lombok.Data;

@Data
public class RecipeTaskDto{
	private String rtId;
	private String recipeId;
	private String image;
	private String step;
	private String optional; 
	    
}
