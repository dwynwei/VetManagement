package com.vetmanagement.vetmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.vetmanagement.vetmanagement.dojo.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

		
}
