package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="feedback")
@NoArgsConstructor 
@AllArgsConstructor
public class Feedback{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long feedbackid;
		
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private CustomerOrder orderId;
	
	private String review;
	private int productRate;
	private int packingRate;
	private int deliveryRate;

	
}
