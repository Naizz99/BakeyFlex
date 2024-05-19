package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
