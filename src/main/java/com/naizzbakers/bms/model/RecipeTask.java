package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="recipe_task")
@NoArgsConstructor 
@AllArgsConstructor
public class RecipeTask{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rtId;
	
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipeId;
	
	
	private String image;
	private String step;
	private boolean optional; 
	    
}
