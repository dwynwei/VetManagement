package com.vetmanagement.vetmanagement.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.vetmanagement.vetmanagement.dojo.Owner;
import com.vetmanagement.vetmanagement.dojo.Pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class OwnerDto {

	
	@NotNull
    @Size(min = 2, max = 10, message = "{input.size.invalid}")
    @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}")
	private String name;
	
	@NotNull
    @Size(min = 2, max = 10, message = "{input.size.invalid}")
    @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}")
	private String lastName;
	
	@NotNull
	@Size(min = 10, max = 25, message = "{address.lenghts}")
	private String address;
	
	@NotNull
    @Email(message = "{invalid.email}")
	private String mailAddress;
	
	@NotNull
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phoneNumber;
	
	@NotNull
    @Size(min = 2, max = 10, message = "{input.size.invalid}")
    @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}")
	private String petName;
	
	@NotNull
    @Size(min = 2, max = 10, message = "{input.size.invalid}")
    @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}")
	private String petType;
	
	@NotNull
    @Size(min = 1, max = 2, message = "{input.size.invalid}")
    @Pattern(regexp = "^[0-9]*$", message = "{input.invalid.msg}")
	private String petAge;
	
	@NotNull
    @Size(min = 1, max = 40, message = "{input.size.invalid}")
	private String petDesc;
	
	
	
	public OwnerDto(//Long id,
			@NotNull @Size(min = 2, max = 10, message = "{input.size.invalid}") @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}") String name,
			@NotNull @Size(min = 2, max = 10, message = "{input.size.invalid}") @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}") String lastName,
			@NotNull @Size(min = 10, max = 25, message = "{address.lenghts}") String address,
			@NotNull @Email(message = "{invalid.email}") String mailAddress,
			@NotNull @Pattern(regexp = "(^$|[0-9]{10})") String phoneNumber,
			@NotNull @Size(min = 2, max = 10, message = "{input.size.invalid}") @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}") String petName,
			@NotNull @Size(min = 2, max = 10, message = "{input.size.invalid}") @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}") String petType,
			@NotNull @Size(min = 1, max = 2, message = "{input.size.invalid}") @Pattern(regexp = "^[0-9]*$", message = "{input.invalid.msg}") String petAge,
			@NotNull @Size(min = 1, max = 40, message = "{input.size.invalid}") String petDesc) {
		//this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.mailAddress = mailAddress;
		this.phoneNumber = phoneNumber;
		this.petName = petName;
		this.petType = petType;
		this.petAge = petAge;
		this.petDesc = petDesc;
	}

	public OwnerDto() {
		
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

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

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getPetAge() {
		return petAge;
	}

	public void setPetAge(String petAge) {
		this.petAge = petAge;
	}

	public String getPetDesc() {
		return petDesc;
	}

	public void setPetDesc(String petDesc) {
		this.petDesc = petDesc;
	}

	@Override
	public String toString() {
		return "OwnerDto [name=" + name + ", lastName=" + lastName + ", address=" + address
				+ ", mailAddress=" + mailAddress + ", phoneNumber=" + phoneNumber + ", petName=" + petName
				+ ", petType=" + petType + ", petAge=" + petAge + ", petDesc=" + petDesc + "]";
	}	
	
}
