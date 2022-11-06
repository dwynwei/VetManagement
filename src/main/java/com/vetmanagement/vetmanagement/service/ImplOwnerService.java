package com.vetmanagement.vetmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.vetmanagement.vetmanagement.config.OwnerDtoMapper;
import com.vetmanagement.vetmanagement.dao.OwnerRepository;
import com.vetmanagement.vetmanagement.dojo.Owner;
import com.vetmanagement.vetmanagement.dto.OwnerDto;

@Service
public class ImplOwnerService implements OwnerService {

	private final OwnerRepository ownerRepository;
	
	@Autowired
	public ImplOwnerService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	@Transactional
	public List<OwnerDto> getAllOwner() {
		List<OwnerDto> ownerDto = new OwnerDtoMapper().ownerToOwnerDtoList(this.ownerRepository.findAll());
		return ownerDto;
	}

	@Override
	@Transactional
	public Optional<Owner> findById(Long id) {
		return this.ownerRepository.findById(id);
	}

	@Override
	@Transactional
	public Owner addOwner(Owner owner) {
		return this.ownerRepository.save(owner);
	}

	@Override
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	public Owner updateOwner(Owner owner) {
		return this.ownerRepository.save(owner);
	}

	@Override
	public void deleteOwner(Long id) {
		this.ownerRepository.deleteById(id);		
	}

}
