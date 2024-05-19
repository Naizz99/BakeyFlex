package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="delivery")
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

	public enum STATUS {
		  PENDING,
		  ONGOING,
		  DELIVERED,
		  CANCELED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long deliveryId;
			
	@ManyToOne
	@JoinColumn(name = "order_id")
	private CustomerOrder orderId;
	
	@ManyToOne
	@JoinColumn(name = "deliverer_id")
	private User delivererId;
	
	private LocalDateTime pickedDate;	
	private LocalDateTime deliveredDate;
	private String delivereryNote;
		
	private STATUS status; 
		    
}
