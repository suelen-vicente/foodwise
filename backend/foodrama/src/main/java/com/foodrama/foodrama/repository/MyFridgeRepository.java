package com.foodrama.foodrama.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.MyFridge;

public interface MyFridgeRepository extends JpaRepository<MyFridge, Long>{
	List<MyFridge> findByUserId(Long userId);
	void deleteByIngredientId(Long ingredientId);
	void deleteByUserId(Long userId);
}
