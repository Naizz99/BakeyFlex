package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="inventory_request")
@NoArgsConstructor 
@AllArgsConstructor
public class InventoryRequest {

	public enum STATUS {
		  PENDING,
		  APPROVED,
		  REQUESTED,
		  PROCESSING,
		  COMPLETED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long irId;
			
	@Column(unique=true)
	private String requestCode;
	
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventoryId;
		
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplierId;
	
	private String specialNote;	
	private int requestedAmount;
	
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
