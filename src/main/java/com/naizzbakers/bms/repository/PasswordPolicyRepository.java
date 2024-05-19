package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.PasswordPolicy;

public interface PasswordPolicyRepository extends JpaRepository<PasswordPolicy, Long> {

}
