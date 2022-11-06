package com.vetmanagement.vetmanagement.service;

import java.util.List;
import java.util.Optional;

import com.vetmanagement.vetmanagement.dojo.Owner;
import com.vetmanagement.vetmanagement.dto.OwnerDto;

public interface OwnerService {

	List<OwnerDto> getAllOwner();
	Optional<Owner> findById(Long id);
	Owner addOwner(Owner owner);
	Owner updateOwner(Owner owner);
	void deleteOwner(Long id);	
}
