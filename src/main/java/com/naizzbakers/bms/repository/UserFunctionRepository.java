package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.UserFunction;

public interface UserFunctionRepository extends JpaRepository<UserFunction, Long> {

}
