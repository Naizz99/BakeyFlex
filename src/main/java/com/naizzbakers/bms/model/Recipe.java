package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="recipe")
@NoArgsConstructor 
@AllArgsConstructor
public class Recipe {

	public enum STATUS {
		  ACTIVE,
		  DEACTIVE,
		  TIMEOUT
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recipeId;
				 
	private String description;
	private String specialNote;
	private String image;  
	
	@ManyToOne
	@JoinColumn(name = "createBy")
	private User createBy;
	
	@ManyToOne
	@JoinColumn(name = "updateBy")
	private User updateBy;
	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
		
	private STATUS status; 
	
}
