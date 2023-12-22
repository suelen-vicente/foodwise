package com.foodrama.foodrama.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RecipeIngredientId {
	
	@Column(name = "ingredient_id")
	private Long ingredientId;
	
	@Column(name = "recipe_id")
	private Long recipeId;

	public RecipeIngredientId(Long ingredientId, Long recipeId) {
		this.ingredientId = ingredientId;
		this.recipeId = recipeId;
	}

	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}
}