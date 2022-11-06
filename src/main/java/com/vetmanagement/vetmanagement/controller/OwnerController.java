package com.vetmanagement.vetmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetmanagement.vetmanagement.config.OwnerDtoMapper;
import com.vetmanagement.vetmanagement.dojo.Owner;
import com.vetmanagement.vetmanagement.dto.OwnerDto;
import com.vetmanagement.vetmanagement.service.OwnerService;


@RestController
@RequestMapping("/owner")
public class OwnerController {
	
	@Qualifier("OwnerService")
	private final OwnerService ownerService;
	
	@Qualifier("OwnerDtoMapper")
	private final OwnerDtoMapper dtoMapper;

	public OwnerController(OwnerService ownerService, OwnerDtoMapper dtoMapper) {
		this.ownerService = ownerService;
		this.dtoMapper = dtoMapper;
	}
	
	@GetMapping("/get_all")
	public ResponseEntity<?> getAllOwners(){
		return ResponseEntity.ok(this.ownerService.getAllOwner());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) throws NullValueInNestedPathException{
		
		Optional<Owner> optional = this.ownerService.findById(id);
		
		if(optional.isPresent())
		{
			OwnerDto ownerDto = this.dtoMapper.ownerToOwnerDtoMapper(optional.get());
			return ResponseEntity.ok(ownerDto);
		}
		
		return ResponseEntity.badRequest().body(new String("Given ID : "+ id + " Not Found In System"));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> addOwner(@RequestBody OwnerDto ownerDto){
		Owner owner = this.dtoMapper.ownerDtoToOwnerMapper(ownerDto);
		this.ownerService.addOwner(owner);		
		return ResponseEntity.ok(this.dtoMapper.ownerToOwnerDtoMapper(owner));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateOwner(@PathVariable(name = "id") Long id, @RequestBody OwnerDto ownerDto){
		Optional<Owner> optional = this.ownerService.findById(id);
		
		if(optional.isPresent()) {
			Owner owner = this.dtoMapper.ownerDtoToOwnerForEdit(optional.get(), ownerDto);
			this.ownerService.updateOwner(owner);
			return ResponseEntity.ok(this.dtoMapper.ownerToOwnerDtoMapper(owner));
		}
		
		return ResponseEntity.badRequest().body(new String("Given ID : "+ id + " Not Found In System"));
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id){
		Optional<Owner> optional = this.ownerService.findById(id);
		
		if(optional.isPresent()) {
			this.ownerService.deleteOwner(id);
			return ResponseEntity.accepted().body(new String(id+" Is Deleted !"));
		}
		
		return ResponseEntity.badRequest().body(new String("Given ID : "+ id + " Not Found In System"));
		
	}
	
}
