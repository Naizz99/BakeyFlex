package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="customer_order")
@NoArgsConstructor 
@AllArgsConstructor
public class CustomerOrder {

	public enum STATUS {
		  PENDING,
		  ACCEPTED,
		  PROCESSING,
		  PACKING,
		  ONGOING,
		  DELIVERED,
		  CANCELED
	}
	
	public enum TYPE {
		  DINEIN,
		  TAKEAWAY,
		  ONLINE
	}
	
	public enum PAYMENT {
		  ONLINE,
		  CARD,
		  CASH,
		  COD
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
		
	@Column(unique=true)
	private String orderCode; 
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;
	
	private LocalDateTime orderedDate;
	private int quantity; 
	private double unitPrice; 
	private double totalPrice; 
	private PAYMENT paymentMethod; 
	private boolean paid;
	
	private String specialNote; 
	private String deliveryNote;
	private String contactPerson; 
	private String alternativeMobile; 
	
	private boolean customerAddress; 
	private String addressLine1; 
	private String addressLine2; 
	private String addressLine3; 
	private String addressLine4; 
	
	@ManyToOne
	@JoinColumn(name = "acceptedBy")
	private User acceptedBy;
	private LocalDateTime acceptedDate;
	
	@ManyToOne
	@JoinColumn(name = "processedBy")
	private User processedBy;
	private LocalDateTime processedDate;
	
	@ManyToOne
	@JoinColumn(name = "packedBy")
	private User packedBy;
	private LocalDateTime packedDate;
	
	@ManyToOne
	@JoinColumn(name = "deliveredBy")
	private User deliveredBy;
	private LocalDateTime pickedDate;
	private LocalDateTime deliveredDate;
	
	@ManyToOne
	@JoinColumn(name = "canceledBy")
	private User canceledBy;
	private LocalDateTime canceledDate;
	
	private STATUS status; 
	private TYPE type; 
		    
}
