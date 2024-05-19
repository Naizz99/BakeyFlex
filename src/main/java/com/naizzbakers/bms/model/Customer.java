package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
		
	@Column(unique=true)
	private String customerCode;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User userId;
	
	private String addressLine1; 
	private String addressLine2; 
	private String addressLine3; 
	private String addressLine4; 
	   
}
