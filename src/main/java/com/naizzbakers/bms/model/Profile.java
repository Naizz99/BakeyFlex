package com.naizzbakers.bms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long profileId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User userId;
	
	private String profilePic; 
	private String coverImage; 

}
