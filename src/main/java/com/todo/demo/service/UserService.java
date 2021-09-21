package com.todo.demo.service;


import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.demo.entity.User;
import com.todo.demo.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService ( UserRepository userRepository) {
		
		this.userRepository = userRepository;
				
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  
		Optional<User> record = userRepository.findUserByName(username);
		if(!record.isPresent()) {
			
			throw new UsernameNotFoundException("user not found - "+username);
		}
		
		User user = record.get();
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}

	public User save(User defaultUser) {
		return userRepository.save(defaultUser);
	}

}
