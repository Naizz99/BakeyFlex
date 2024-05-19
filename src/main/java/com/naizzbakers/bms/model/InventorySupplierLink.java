package com.naizzbakers.bms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="inventory_supplier_link")
@NoArgsConstructor 
@AllArgsConstructor
public class InventorySupplierLink {

	public enum STATUS {
		  AVAILABLE,
		  DISABLE,
		  FINISHED,
		  REQUESTED,
		  EXPIRED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long islId;
			
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventoryId;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplierId;
	
	private String specialNote;
	private String image;  
	
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
