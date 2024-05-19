package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeid;
		
	@Column(unique=true)
	private String employeeCode;
	
	private String address;
	private String mobile2;
	private String contactPersonName;
	private String contactPersonNumber;
			
	@OneToOne
	@JoinColumn(name = "user_id")
	private User userId;
	    
}
