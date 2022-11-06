package com.vetmanagement.vetmanagement.dojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "owners")
public class Owner {
	
	@Id
	@GeneratedValue(
	   strategy = GenerationType.SEQUENCE,
	   generator = "entity_id_seq"
	)
	@SequenceGenerator(
	   name = "entity_id_seq",
	   sequenceName = "global_id_sequence",
	   allocationSize = 1,
	   initialValue = 1	   
	)
	@Column(name = "owner_id",
			unique = true,
			updatable = false,
			nullable = false)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String lastName;
	
	@Column
	private String address;
	
	@Column
	private String mailAddress;
	
	@Column
	private String phoneNumber;
	
	@OneToMany(mappedBy = "owner",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
			orphanRemoval = true)
	private List<Pet> pets = new ArrayList<>();

	

	public Owner(Long id, String name, String lastName, String address, String mailAddress, String phoneNumber,
			List<Pet> pets) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.mailAddress = mailAddress;
		this.phoneNumber = phoneNumber;
		this.pets = pets;
	}

	public Owner() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
	@Override
	public String toString() {
		return "Owner [id=" + id + ", name=" + name + ", lastName=" + lastName + ", address=" + address
				+ ", mailAddress=" + mailAddress + ", phoneNumber=" + phoneNumber + ", pets=" + pets + "]";
	}
	
}
