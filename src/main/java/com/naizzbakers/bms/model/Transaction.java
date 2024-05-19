package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="transaction")
@NoArgsConstructor 
@AllArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
		
	@Column(unique=true)
	private String transactionCode; 
	
	private String crdr; 
	private double amount; 
	private String shortDescription; 
	private String description; 
	private String specialNote;
	private String image;  
			
	private int createBy;
	private int updateBy;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
				    
}
