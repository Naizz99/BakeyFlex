package com.naizzbakers.bms.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="task_log")
@NoArgsConstructor 
@AllArgsConstructor
public class TaskLog{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskId;
		
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employeeId;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private CustomerOrder orderId;
	
	private String task;
	private String log;
	private LocalDateTime createDate;
	
}
