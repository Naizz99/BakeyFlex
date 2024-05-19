package com.naizzbakers.bms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InventoryDto {
	private long inventoryId;
    private String inventorySerial; 
    private long inventoryType;	
    private String inventoryName; 
    private String description; 
    private String specialNote;
    private String image;  
    private String unitId;
    private int availableAmount;
    private double unitPrice;
    private String firstExDate; // Consider using LocalDate if handling dates in Java
    private String createBy;
    private String updateBy;
    private LocalDateTime createDate; // Consider using LocalDate if handling dates in Java
    private LocalDateTime updateDate; // Consider using LocalDate if handling dates in Java
    private String status; 		    
}
