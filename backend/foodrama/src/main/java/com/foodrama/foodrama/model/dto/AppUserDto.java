package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.AppUser;

public record AppUserDto(Long id, String name) {
	public AppUserDto(AppUser user) {
		this(user.getId(), 
				user.getName());
	}
	
	public AppUser toEntity() {
		AppUser user = new AppUser();
		user.setName(this.name());
        
        return user;
	}
}
