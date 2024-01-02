package com.foodrama.foodrama.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_seq")
	@SequenceGenerator(name = "app_user_seq", sequenceName = "app_user_seq", allocationSize = 1)
	private Long id;

	private String name;

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
}
