package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="supplier")
@NoArgsConstructor 
@AllArgsConstructor
public class Supplier {

	public enum STATUS {
		ACTIVE,
		DEACTIVE,
		SUSPENDED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long supplierId;
		
	@Column(unique=true)
	private String supplierSerial; 
	
	private String supplierName;
	private String contactPerson; 
	private String mobile1;
	private String mobile2;
	private String address;
	private String email; 
	private String image;
	private String specialNote;
	  	
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
