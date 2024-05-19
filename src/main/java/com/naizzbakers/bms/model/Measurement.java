package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="measurement")
@NoArgsConstructor 
@AllArgsConstructor
public class Measurement{

	public enum STATUS {
		  ACTIVE,
		  DEACTIVE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mId;
		
	@Column(unique=true)
	private String unit; 
	
	private String unitName; 
	private String description; 
			
	private STATUS status; 
		    
}
