package com.naizzbakers.bms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.naizzbakers.bms.repository.UserRepository;
import com.naizzbakers.bms.service.PasswordService;
import com.naizzbakers.bms.service.RoleService;
import com.naizzbakers.bms.model.Password;
import com.naizzbakers.bms.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private RoleService roledService;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		
		Password password = new Password();

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}else {
			password = passwordService.findByUserId(user);
			return new MyUserDetails(user,password,roledService.listAll());
		}
		
		
	}

}

