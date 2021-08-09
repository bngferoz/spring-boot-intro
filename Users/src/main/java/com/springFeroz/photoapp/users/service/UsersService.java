package com.springFeroz.photoapp.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springFeroz.photoapp.users.shared.UserDTO;

public interface UsersService extends UserDetailsService{
	UserDTO createUser(UserDTO userDetails);
	UserDTO getUserDetailsByEmail(String email);
	
}
