package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="product")
@NoArgsConstructor 
@AllArgsConstructor
public class Product {

	public enum STATUS {
		  ACTIVE,
		  DEACTIVE,
		  SOLDOUT,
		  EXPIRED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
		
	@Column(unique=true)
	private String productSerial; 
	
	@ManyToOne
	@JoinColumn(name = "product_type")
	private ProductType productType;
	
	private String productName; 
	private String shortDescription; 
	private String longDescription;
	private String specialNote;
	private String image;  
	private double unitPrice;  
	private double discountRate;  
	
	@ManyToOne
	@JoinColumn(name = "createBy")
	private User createBy;
	
	@ManyToOne
	@JoinColumn(name = "updateBy")
	private User updateBy;
	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
		
	private STATUS status; 
	
	@OneToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipeId;
	    
}
