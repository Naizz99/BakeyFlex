package com.naizzbakers.bms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="user")
@NoArgsConstructor 
@AllArgsConstructor
public class User{

	public enum STATUS {
		  ACTIVE,
		  DEACTIVE,
		  SUSPENDED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	private String firstName; 
	private String middleName; 
	private String lastName; 
	private String mobile;
	private String email; 
	private String gender; 
	private String birthDay;
	private String nic;
	
	@Column(unique=true)
	private String username;
	
	private boolean logged; 
	
	@ManyToOne
	@JoinColumn(name = "createBy")
	private User createBy;
	
	@ManyToOne
	@JoinColumn(name = "updateBy")
	private User updateBy;
	
//	private String createBy;
//	private String updateBy;
	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
		
	private STATUS status; 
	
	@ManyToOne
	@JoinColumn(name = "role")
	private Role role;
	    
}
