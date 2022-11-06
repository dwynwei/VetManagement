package com.vetmanagement.vetmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vetmanagement.vetmanagement.dojo.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

}
