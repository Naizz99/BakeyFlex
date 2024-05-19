package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
