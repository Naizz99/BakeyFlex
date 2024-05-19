package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "password_policy")
@NoArgsConstructor 
@AllArgsConstructor
public class PasswordPolicy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long policyId;
	
	private String policy;
	private String data;
	
	private boolean active;
}
