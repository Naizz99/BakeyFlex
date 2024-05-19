package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="role")
@NoArgsConstructor 
@AllArgsConstructor
public class Role{

	public enum STATUS {
		  ACTIVE,
		  DEACTIVE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roleId;
		
	@Column(unique=true)
	private String role; 
	
	private String roleName; 
			
	private STATUS status; 
		    
}
