package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="ingredient")
@NoArgsConstructor 
@AllArgsConstructor
public class Ingredient{
	
	public enum STATUS {
		  ACTIVE,
		  DEACTIVE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ingredientId;
	
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipeId;
	
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventoryId;
	
	private String image;
	private String ingredientName;
	
	private int amount;
	
	private STATUS statuc; 
	    
}
