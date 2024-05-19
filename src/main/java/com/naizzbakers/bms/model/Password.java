package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="password")
@NoArgsConstructor 
@AllArgsConstructor
public class Password{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User userId;
	
	private String password;
	private LocalDateTime lastUpdated;
	    
}
