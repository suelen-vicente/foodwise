package com.foodrama.foodrama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long>{
	
	List<ShoppingList> findByUserId(Long userId);
	
	Optional<ShoppingList> findByUserIdAndIngredientId(Long userId, Long ingredientId);
	
	void deleteByIngredientId(Long ingredientId);
	
	void deleteByUserId(Long userId);
}
