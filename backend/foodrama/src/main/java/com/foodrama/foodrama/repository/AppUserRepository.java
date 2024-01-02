package com.foodrama.foodrama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
}
