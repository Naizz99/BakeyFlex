package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "role_function")
@NoArgsConstructor 
@AllArgsConstructor
public class RoleFunctionLink{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uflId;
	
	@ManyToOne
	@JoinColumn(name = "functionId")
	private UserFunction functionId;
	
	@ManyToOne
	@JoinColumn(name = "role")
	private Role role;
	
	private boolean active;
	
	private int createBy;
	private int updateBy;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
}
