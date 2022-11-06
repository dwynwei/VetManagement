package com.vetmanagement.vetmanagement.dojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Pet{

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
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String type;
	
	@Column
	private String age;
	
	@Column
	private String desc;
	
	@ToStringExclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_id")
	private Owner owner;

	public Pet(Long id, String name, String type, String age, String desc, Owner owner) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.age = age;
		this.desc = desc;
		this.owner = owner;
	}

	public Pet() {
		
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", type=" + type + ", age=" + age + ", desc=" + desc + ", owner="
				+ owner + "]";
	}
	
	
	
}
