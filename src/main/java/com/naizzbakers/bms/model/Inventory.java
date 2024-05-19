package com.naizzbakers.bms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="inventory")
@NoArgsConstructor 
@AllArgsConstructor
public class Inventory {

	public enum STATUS {
		  AVAILABLE,
		  DISABLE,
		  FINISHED,
		  REQUESTED,
		  EXPIRED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventoryId;
		
	@Column(unique=true)
	private String inventorySerial; 
	
	@ManyToOne
	@JoinColumn(name = "inventory_type")
	private InventoryType inventoryType;
	
	private String inventoryName; 
	private String description; 
	private String specialNote;
	private String image;  
	
	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Measurement unitId;
	
	private int availableAmount;
	private double unitPrice;
	private String firstExDate;
	
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
