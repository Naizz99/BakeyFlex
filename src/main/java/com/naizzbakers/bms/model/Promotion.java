package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "promotion")
@NoArgsConstructor 
@AllArgsConstructor
public class Promotion {
	
	public enum STATUS {
		  ACTIVE,
		  DEACTIVE,
		  TIMEOUT,
		  SOLDOUT
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long promotionId;
	
	@Column(unique=true)
	private String promoCode;
	
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private boolean counting;
	private String title;
	private String description;
	private String specialNote;
	
	private String image;
	private String backgroundImage;
	private boolean onlyImage;
	
	private int createBy;
	private int updateBy;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	private STATUS status;
}
