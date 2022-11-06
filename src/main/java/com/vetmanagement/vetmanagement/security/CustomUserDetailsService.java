package com.vetmanagement.vetmanagement.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vetmanagement.vetmanagement.dao.UserRepository;
import com.vetmanagement.vetmanagement.dojo.User;
import com.vetmanagement.vetmanagement.exception.ResourceNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found related with MAIL : "+ email));
		
		return UserPrincipal.create(user);
		
	}
	
	public UserDetails loadUserById(Long id) {
		
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		
		return UserPrincipal.create(user);
		
	}
	
}
