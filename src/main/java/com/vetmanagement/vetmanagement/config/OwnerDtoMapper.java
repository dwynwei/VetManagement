package com.vetmanagement.vetmanagement.config;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DialogOwner;

import org.springframework.context.annotation.Configuration;

import com.vetmanagement.vetmanagement.dojo.Owner;
import com.vetmanagement.vetmanagement.dojo.Pet;
import com.vetmanagement.vetmanagement.dto.OwnerDto;

@Configuration
public class OwnerDtoMapper {
	

	public Owner ownerDtoToOwnerMapper(OwnerDto dto) {
		Owner owner = new Owner();
		owner.setName(dto.getName());
		owner.setLastName(dto.getLastName());
		owner.setAddress(dto.getAddress());
		owner.setMailAddress(dto.getMailAddress());
		owner.setPhoneNumber(dto.getPhoneNumber());
		
		List<Pet> petList = new ArrayList<>();
		
		Pet petData = new Pet();
		petData.setName(dto.getPetName());
		petData.setType(dto.getPetType());
		petData.setAge(dto.getPetAge());
		petData.setDesc(dto.getPetDesc());
		petData.setOwner(owner);
		petList.add(petData);
		
		owner.setPets(petList);
		return owner;		
	}
	
	
	public OwnerDto ownerToOwnerDtoMapper(Owner owner) {
		OwnerDto ownerDto = new OwnerDto();
		//ownerDto.setId(owner.getId());
		ownerDto.setName(owner.getName());
		ownerDto.setLastName(owner.getLastName());
		ownerDto.setAddress(owner.getAddress());
		ownerDto.setMailAddress(owner.getMailAddress());
		ownerDto.setPhoneNumber(owner.getPhoneNumber());
		ownerDto.setPetName(owner.getPets().get(0).getName());
		if(owner.getPets().size() > 0) {
			ownerDto.setPetType(owner.getPets().get(0).getType());
			ownerDto.setPetAge(owner.getPets().get(0).getAge());
			ownerDto.setPetDesc(owner.getPets().get(0).getDesc());
		}
		
		return ownerDto;
	}
	
	
	public List<OwnerDto> ownerToOwnerDtoList(List<Owner> ownerList){
		List<OwnerDto> ownerDtoList = new ArrayList<>();
		for (Owner owner : ownerList) {
			ownerDtoList.add(ownerToOwnerDtoMapper(owner));
		}
		return ownerDtoList;
	}
	
	public Owner ownerDtoToOwnerForEdit(Owner owner, OwnerDto ownerDto) {
		owner.setName(ownerDto.getName());
		owner.setLastName(ownerDto.getLastName());
		owner.setAddress(ownerDto.getAddress());
		owner.setMailAddress(ownerDto.getMailAddress());
		owner.setPhoneNumber(ownerDto.getPhoneNumber());
		owner.getPets().get(0).setName(ownerDto.getPetName());
		owner.getPets().get(0).setType(ownerDto.getPetType());
		owner.getPets().get(0).setAge(ownerDto.getPetAge());
		owner.getPets().get(0).setDesc(ownerDto.getPetDesc());
		owner.getPets().get(0).setOwner(owner);
		return owner;		
	}
	
}
