package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="product_type")
@NoArgsConstructor 
@AllArgsConstructor
public class ProductType {

	public enum STATUS {
		  ACTIVE,
		  DEACTIVE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ptId;
		
	private String productType; 
	private String productTypeName; 
	private String description;  
	
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
