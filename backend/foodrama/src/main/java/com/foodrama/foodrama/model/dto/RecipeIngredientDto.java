package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.QuantityUnit;
import com.foodrama.foodrama.model.RecipeIngredient;

public record RecipeIngredientDto(IngredientDto ingredient, Double ingredientQuantity, QuantityUnit quantityUnit) {
	
	public RecipeIngredientDto(RecipeIngredient recipeIng) {
		this(new IngredientDto(recipeIng.getIngredient()), 
				recipeIng.getIngredientQuantity(),
				QuantityUnit.fromLabel(recipeIng.getQuantityUnit()));
	}
	
	public RecipeIngredient toEntity() {
		RecipeIngredient recipeIng = new RecipeIngredient();
		recipeIng.setIngredient(this.ingredient().toEntity());
		recipeIng.setIngredientQuantity(this.ingredientQuantity());
		recipeIng.setQuantityUnit(this.quantityUnit().getLabel());
		
        return recipeIng;
	}
}
