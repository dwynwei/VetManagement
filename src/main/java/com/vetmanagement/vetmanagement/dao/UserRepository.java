package com.vetmanagement.vetmanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vetmanagement.vetmanagement.dojo.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
}
