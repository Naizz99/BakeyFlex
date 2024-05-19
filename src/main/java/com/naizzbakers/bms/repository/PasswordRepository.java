package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Password;
import com.naizzbakers.bms.model.User;

public interface PasswordRepository extends JpaRepository<Password, Long> {

	Password findByUserId(User userId);

	void deleteByUserId(User userId);

	Password getByUserId(User user);

}
