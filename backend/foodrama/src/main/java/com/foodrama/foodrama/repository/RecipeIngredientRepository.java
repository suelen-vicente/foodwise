package com.foodrama.foodrama.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.RecipeIngredient;
import com.foodrama.foodrama.model.RecipeIngredientId;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientId>{
	Set<RecipeIngredient> findByRecipeIngredientId_RecipeId(Long recipeId);
	Optional<RecipeIngredient> findByRecipeIngredientId_RecipeIdAndRecipeIngredientId_IngredientId(Long recipeId, Long ingredientId);
	void deleteAllByRecipeIngredientId_RecipeId(Long recipeId);
}
