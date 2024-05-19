package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Profile;
import com.naizzbakers.bms.model.User;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Profile getByUserId(User user);

}
