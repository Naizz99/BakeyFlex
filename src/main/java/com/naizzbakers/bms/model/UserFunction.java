package com.naizzbakers.bms.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_function")
@NoArgsConstructor 
@AllArgsConstructor
public class UserFunction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long functionId;
	
	private String functionName;
	private String functionAction;
	
	private boolean isParent;
	private int parentId;
	
	private boolean linked;
	private boolean active;
	
	private int createBy;
	private int updateBy;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
}
