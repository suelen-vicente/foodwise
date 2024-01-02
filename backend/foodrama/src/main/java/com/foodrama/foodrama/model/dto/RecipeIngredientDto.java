package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.QuantityUnit;
import com.foodrama.foodrama.model.Recipe;
import com.foodrama.foodrama.model.RecipeIngredient;
import com.foodrama.foodrama.model.RecipeIngredientId;

public record RecipeIngredientDto(IngredientDto ingredient, Double ingredientQuantity, QuantityUnit quantityUnit) {
	
	public RecipeIngredientDto(RecipeIngredient recipeIng) {
		this(new IngredientDto(recipeIng.getIngredient()), 
				recipeIng.getIngredientQuantity(),
				QuantityUnit.fromLabel(recipeIng.getQuantityUnit()));
	}
	
	public RecipeIngredient toEntity(Recipe recipe, Ingredient ingredient) {
		RecipeIngredient recipeIng = new RecipeIngredient();
		recipeIng.setRecipeIngredientId(new RecipeIngredientId(ingredient.getId(), recipe.getId()));
		recipeIng.setIngredient(ingredient);
		recipeIng.setRecipe(recipe);
		recipeIng.setIngredientQuantity(this.ingredientQuantity());
		recipeIng.setQuantityUnit(this.quantityUnit().getLabel());
		
        return recipeIng;
	}
}
