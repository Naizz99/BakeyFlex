package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="product_image")
@NoArgsConstructor 
@AllArgsConstructor
public class ProductImage{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long piId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productId;
	
	private String image;
	private String alternativeText;
	
	private boolean active; 
	    
}
