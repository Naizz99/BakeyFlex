package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getByRole(String role);

}
