package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="inventory_type")
@NoArgsConstructor 
@AllArgsConstructor
public class InventoryType {

	public enum STATUS {
		  ACTIVE,
		  DEACTIVE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itId;
		
	private String inventoryType; 
	private String inventoryTypeName; 
	private String description;  
	
	private int createBy;
	private int updateBy;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
		
	private STATUS status; 
	    
}
