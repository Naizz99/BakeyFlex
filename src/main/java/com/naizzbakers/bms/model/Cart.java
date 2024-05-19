package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	    
	@ManyToOne
	@JoinColumn(name = "customer_id")
    private Customer customerId;
	
	@ManyToOne
	@JoinColumn(name = "productId")
    private Product productId;
	
	private int quantity;
	private double unitPrice;
	private double totalAmount;
	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
