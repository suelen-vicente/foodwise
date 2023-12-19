package com.foodrama.foodrama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.MyFridge;

public interface MyFridgeRepository extends JpaRepository<MyFridge, Long>{
	
	List<MyFridge> findByUserId(Long userId);
	
	Optional<MyFridge> findByUserIdAndIngredientId(Long userId, Long ingredientId);
	
	void deleteByIngredientId(Long ingredientId);
	
	void deleteByUserId(Long userId);
}
