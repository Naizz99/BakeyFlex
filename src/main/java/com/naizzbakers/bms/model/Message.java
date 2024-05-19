package com.naizzbakers.bms.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "message")
@NoArgsConstructor 
@AllArgsConstructor
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;
	
	@ManyToOne
	@JoinColumn(name = "sentFrom")
    private User sentFrom;
	
	@ManyToOne
	@JoinColumn(name = "sentTo")
    private User sentTo;
		
	private LocalDate sentDate;
	private LocalTime sentTime;
	private String message;
	private boolean readStatus;
}
