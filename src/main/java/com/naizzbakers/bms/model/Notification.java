package com.naizzbakers.bms.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "notification")
@NoArgsConstructor 
@AllArgsConstructor
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ntfId;
				
	@ManyToOne
	@JoinColumn(name = "userId")
    private User userId;
	
	private LocalDate receiveDate;
	private LocalTime receiveTime;
	private String topic;
	private String notification;
	
	private boolean readStatus;
	
}
